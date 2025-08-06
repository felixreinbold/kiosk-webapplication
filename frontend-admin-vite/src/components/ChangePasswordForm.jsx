import React, { useState } from "react";
import { changePassword } from "../services/api";

export default function ChangePasswordForm() {
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await changePassword(oldPassword, newPassword);
      alert("Passwort wurde erfolgreich geändert!");
      setOldPassword("");
      setNewPassword("");
    } catch (err) {
      alert("Fehler beim Ändern des Passworts: " + err.message);
    }
  };

  return (
    <form onSubmit={handleSubmit}className="change-password-form">
      <h3 className="change-password-title">🔐 Passwort ändern</h3>
      <input
        type="password"
        placeholder="Altes Passwort"
        value={oldPassword}
        onChange={(e) => setOldPassword(e.target.value)}
        required
        className="change-password-input"
      />
      <input
        type="password"
        placeholder="Neues Passwort"
        value={newPassword}
        onChange={(e) => setNewPassword(e.target.value)}
        required
        className="change-password-input"
      />
      <button type="submit" className="change-password-button">Ändern</button>
    </form>
  );
}
