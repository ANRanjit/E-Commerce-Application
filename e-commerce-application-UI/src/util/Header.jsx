import React from "react";
import { Link } from "react-router-dom";
import { CgProfile } from "react-icons/cg";
import { IoIosArrowDown } from "react-icons/io";
import { CiSearch } from "react-icons/ci";
import { BsBox } from "react-icons/bs";
import { GoHeart } from "react-icons/go";

const Header = () => {
  return (
    <div className="flex item-center justify-around text-slate-950 text-slate-100 py-2  border-b-2 border-slate-1 text-center text-lg">
      <Link to={"/"}>
        <img
          src="https://static-assets-web.flixcart.com/batman-returns/batman-returns/p/images/fkheaderlogo_exploreplus-44005d.svg"
          width="160"
          height="40"
          title="Flipkart"
        />
      </Link>
      <section className="flex items-center gap-2 bg-blue-100 w-[32rem] focus:ring-sky-500 focus:ring-1 rounded-md">
        <CiSearch />
        <input
          type="search"
          placeholder="Search for Products, Brands and More .."
          className="w-full rounded-md bg-blue-100 focus:outline-none sm:text-sm h-10 placeholder:italic placeholder:text-gray-400 block pr-3 shadow-sm "
        />
      </section>
      <Link to={"/login"} className="relative group transition-all">
        <p className="flex items-center justify-around  w-24  rounded-md hover:bg-blue-700 hover:text-white">
          <CgProfile />
          Login{" "}
          <IoIosArrowDown className="rotate-180 transition-all hover:rotate-0" />
        </p>
        <div className=" text-sm flex justify-around absolute top-10 hidden w-60 flex-col gap-1 rounded-md bg-white py=3  shadow-md transition-all group-hover:flex ">
          <Link to={"/register"} className="flex item-center gap-5 ">
            {" "}
            New customer? <p className="text-blue-600 ">SignUp</p>
          </Link>
          {/* <Link to={"#"} className="flex  gap-2">
            <CgProfile />
            My Profile
          </Link>
          <Link to={"#"} className="flex gap-2 ">
            <BsBox />
            Orders
          </Link>
          <Link to={"/wishlist"} className="flex gap-2 ">
            <GoHeart /> Wishlist
          </Link> */}
        </div>
      </Link>
      <Link to={"/register"} className="flex items-center gap-2 mb-2  ">
        <img
          src="https://static-assets-web.flixcart.com/batman-returns/batman-returns/p/images/Store-9eeae2.svg"
          alt="Become a Seller"
          class="_1XmrCc"
        />
        Become a seller
      </Link>
    </div>
  );
};

export default Header;
