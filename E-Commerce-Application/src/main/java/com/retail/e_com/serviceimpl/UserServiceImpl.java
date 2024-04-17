package com.retail.e_com.serviceimpl;


import java.util.Random;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retail.e_com.cache.CacheStore;
import com.retail.e_com.entity.Customer;
import com.retail.e_com.entity.Seller;
import com.retail.e_com.entity.User;
import com.retail.e_com.enums.UserRole;
import com.retail.e_com.exception.InvalidOtpException;
import com.retail.e_com.exception.InvalidUserRoleException;
import com.retail.e_com.exception.OtpExpiredException;
import com.retail.e_com.exception.RegistrationSessionExpiredException;
import com.retail.e_com.exception.UserExistedByEmailException;
import com.retail.e_com.repository.UserRepo;
import com.retail.e_com.request_dto.OtpRequest;
import com.retail.e_com.request_dto.UserRequest;
import com.retail.e_com.response_dto.UserResponse;
import com.retail.e_com.service.MailService;
import com.retail.e_com.service.UserService;
import com.retail.e_com.utility.MessageModel;
import com.retail.e_com.utility.ResponseStructure;
import com.retail.e_com.utility.SimpleResponseStructure;

import jakarta.mail.MessagingException;



@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	private UserRepo userRepository;
	private CacheStore<String>otpCache;
	private CacheStore<User>userCache;
	private ResponseStructure<UserResponse>responseStructure;
	private SimpleResponseStructure simpleResponseStructure;
	private MailService mailService;
	
	@Override
	public ResponseEntity<SimpleResponseStructure> userRegistration(UserRequest userReqeust) {

		if(userRepository.existsByEmail(userReqeust.getEmail()))
			throw new UserExistedByEmailException("invalid user");

		
		User user =mapToChildEntity(userReqeust);
		String otp =generatedOTP();
		System.out.println(otp);
		
		//Send mail with otp
		try {
			sendMail(user,otp);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		otpCache.add(userReqeust.getEmail(), otp);
		userCache.add(userReqeust.getEmail(), user);
		return ResponseEntity.ok(simpleResponseStructure.setStatuscode(HttpStatus.CREATED.value()).setMessage("Verify OTP sent through mail to complete registration"));

	}
	
	private void sendMail(User user, String otp) throws MessagingException{
	MessageModel model=MessageModel.builder()
		.to(user.getEmail())
		.subject("verify your otp")
		.text(
				"<p> Hi,<br>"
						+"Thanks for your intrest in E-Commerce-Application"
						+"please verify your mail ID using the otp given below.</p>"
						+"<br>"
						+ "<h1>"+otp+"</h1>"
						+"<br>"
						+"<p>please ignore if its not you</p>"
						+"<br>"
						+"with best regards"
						+"<h3>E-Commerce-Application</h3>"
						+"<img src='https://i.postimg.cc/136S0Pjc/iphone.jpg' hieght='200px'>"
				).build();
		mailService.sendMailMessage(model);
		
	}

	private String generatedOTP() {
		return String.valueOf(new Random().nextInt(100000,999999));
	}

	@SuppressWarnings("unchecked")
	private <T extends User> T mapToChildEntity(UserRequest userReqeust) {
		UserRole role =userReqeust.getUserRole();
		User user =null;
		switch (role) {
		case SELLER -> user =new Seller();
		case CUSTOMER-> user = new Customer();
		default -> throw new InvalidUserRoleException(" invalid user");

		}

		user.setDisplayName(userReqeust.getName());
		user.setUsername(userReqeust.getEmail().split("@gmail.com")[0]);
		user.setEmail(userReqeust.getEmail());
		user.setPassword(userReqeust.getPassword());
		user.setUserRole(role);
		user.setIsEmailVerified(false);
		user.setIsDeleted(false);
		return (T) user;		
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(OtpRequest otpRequest) {
		if(otpCache.get(otpRequest.getEmail())==null)
			throw new OtpExpiredException(" Otp Expired");
		if(!otpCache.get(otpRequest.getEmail()).equals(otpRequest.getOtp())) 
			throw new InvalidOtpException("invalid otp");
		User user =userCache.get("user");
		if(user==null)
			throw new RegistrationSessionExpiredException("Seesion Expired");
		user.setIsEmailVerified(true);
//		User user2 = userRepository.save(user);

		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.CREATED.value())
				.setMessage("otp genrated successfully")
				.setData(mapToUserResponse(user)));

	}
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().displayName(user.getDisplayName())
				.userId(user.getUserId())
				.userName(user.getUsername())
				.email(user.getEmail())
				.isDeleted(user.getIsDeleted())
				.isEmailVerified(user.getIsEmailVerified())
				.userRole(user.getUserRole()).build();

	}
	
}
