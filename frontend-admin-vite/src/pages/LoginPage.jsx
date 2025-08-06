import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { saveToken } from "../utils/auth";
import { resetPassword } from "../services/api";

export default function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const res = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      if (res.ok) {
        const data = await res.json();
        saveToken(data.token); // ✅ speichert auch Ablaufzeit
        navigate("/dashboard"); // ✅ automatische Weiterleitung
      } else {
        alert("Login fehlgeschlagen");
      }
    } catch (err) {
      alert("Fehler beim Verbinden mit dem Server");
    }
  };

  const handleReset = async () => {
  try {
    const msg = await resetPassword();
    alert(msg);
  } catch (err) {
    alert("Fehler beim Zurücksetzen des Passworts.");
  }
 };


  return (
    <div className="login-container">
      <h2 className="login-title">Admin Login</h2>
  <   form onSubmit={handleLogin} className="login-form">
        <input
          className="login-input"
          placeholder="Benutzername"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
    />
    <input
      className="login-input"
      type="password"
      placeholder="Passwort"
      value={password}
      onChange={(e) => setPassword(e.target.value)}
    />
    <button className="login-button" type="submit">Einloggen</button>
  </form>
  <button className="login-reset-button" type="button" onClick={handleReset}>
    🔑 Passwort vergessen?
  </button>
</div>

  );
}
