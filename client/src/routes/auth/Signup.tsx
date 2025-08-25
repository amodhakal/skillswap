import { useState, type FormEvent } from "react";
import { Link, useNavigate } from "react-router";
import axios from "axios";

export default function Signup() {
  const navigateTo = useNavigate();
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <div className="min-h-screen w-screen flex justify-center items-center">
      <div className="bg-white shadow rounded-xl flex flex-col p-8">
        <div className="flex flex-col items-center mb-2">
          <h1 className="text-3xl font-extrabold mb-0.5">Create an account</h1>
          <p className="">Finally master that skill you always wanted.</p>
        </div>
        <form className="flex flex-col gap-2" onSubmit={handleSignup}>
          <div className="flex flex-col gap-1">
            <label htmlFor="name" className="">
              Enter your name
            </label>
            <input
              required
              value={name}
              onChange={(ev) => setName(ev.target.value)}
              type="text"
              name="name"
              className="border border-gray-300 rounded-md py-1 px-3"
            />
          </div>

          <div className="flex flex-col gap-1">
            <label htmlFor="email" className="">
              Enter your email
            </label>
            <input
              required
              value={email}
              onChange={(ev) => setEmail(ev.target.value)}
              type="email"
              name="email"
              className="border border-gray-300 rounded-md py-1 px-3"
            />
          </div>

          <div className="flex flex-col gap-1">
            <label htmlFor="password" className="">
              Enter your password
            </label>
            <input
              required
              value={password}
              onChange={(ev) => setPassword(ev.target.value)}
              type="password"
              name="password"
              className="border border-gray-300 rounded-md py-1 px-3"
            />
          </div>

          <button
            type="submit"
            className="mt-2 rounded-md hover:cursor-pointer active:bg-blue-400 hover:bg-blue-500 bg-blue-600 text-white py-2"
          >
            Sign up
          </button>
        </form>
        <div className="mt-2">
          <p className="flex justify-end">
            Have an account,
            <Link to="/auth/signin" className="ml-1 text-blue-600">
              sign in!
            </Link>
          </p>
        </div>
      </div>
    </div>
  );

  async function handleSignup(ev: FormEvent) {
    ev.preventDefault();
    if (!name || !email || !password) {
      return;
    }

    await axios
      .post("/api/auth/signup", { name, email, password })
      .then((res) => {
        if (!res) {
          return;
        }

        const { token, expiration } = res.data;
        localStorage.setItem("token", token);
        localStorage.setItem("expiration", expiration);
        navigateTo("/");
      })
      .catch((err) => {
        alert(err.response.data.error);
        console.error(err);
        return;
      });
  }
}
