import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
export default function useToken() {
  const [token, setToken] = useState("");
  const navigateTo = useNavigate();

  useEffect(() => {
    const storedExpiration = localStorage.getItem("expiration");
    if (!storedExpiration) {
      return navigateToAuth();
    }

    const expirationDate = new Date(storedExpiration);
    const currentDate = new Date();

    if (expirationDate.getMilliseconds() >= currentDate.getMilliseconds()) {
      navigateTo("/signin");
      return navigateToAuth();
    }

    const storedToken = localStorage.getItem("token");
    if (!storedToken) {
      return navigateToAuth();
    }

    setToken(token);

    function navigateToAuth() {
      navigateTo("auth/signin");
    }
  }, [navigateTo, token]);

  return token;
}
