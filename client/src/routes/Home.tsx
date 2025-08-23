import useToken from "../hooks/useToken";

export default function Home() {
  useToken();
  return <div className="">Home</div>;
}
