import React, { useEffect, useState } from "react";
import {
  getAngebote,
  createAngebot,
  updateAngebot,
  deleteAngebot,
} from "../services/api";
import AngebotForm from "../components/AngebotForm";
import { useNavigate } from "react-router-dom";
import { getToken, logout } from "../utils/auth";
import ChangePasswordForm from "../components/ChangePasswordForm";

export default function DashboardPage() {
  const [angebote, setAngebote] = useState([]);
  const [edit, setEdit] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    loadAngebote();

    // Auto-Logout bei Token-Ablauf
    const expiry = localStorage.getItem("tokenExpiry");
    if (expiry) {
      const now = new Date().getTime();
      const timeout = expiry - now;
      if (timeout > 0) {
        const timer = setTimeout(() => {
          logout();
          navigate("/login");
        }, timeout);
        return () => clearTimeout(timer);
      } else {
        logout();
        navigate("/login");
      }
    }
  }, []);

  const loadAngebote = async () => {
    try {
      const daten = await getAngebote();
      setAngebote(daten);
    } catch (err) {
      console.error(err);
      alert("Fehler beim Laden der Angebote");
    }
  };

  const handleCreate = async (angebot) => {
    try {
      await createAngebot(angebot);
      await loadAngebote();
    } catch (err) {
      alert(err.message);
    }
  };

  const handleUpdate = async (angebot) => {
    try {
      await updateAngebot(edit.id, angebot);
      setEdit(null);
      await loadAngebote();
    } catch (err) {
      alert(err.message);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm("Wirklich löschen?")) {
      try {
        await deleteAngebot(id);
        await loadAngebote();
      } catch (err) {
        alert(err.message);
      }
    }
  };

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

return (
  <div className="dashboard-container">
    <div className="dashboard-header">
      <h2>Dashboard: Angebote verwalten</h2>
      <button className="logout-button" onClick={handleLogout}>Logout</button>
    </div>

    <AngebotForm onSave={edit ? handleUpdate : handleCreate} existing={edit} />

    <hr className="dashboard-separator" />

    <div className="angebote-list">
      {angebote.map((a) => (
        <div key={a.id} className="angebot-card">
          <h3>{a.titel}</h3>
          <p>{a.beschreibung}</p>
          <img
            src={a.bildBase64 ? `data:image/jpeg;base64,${a.bildBase64}` : a.imageUrl}
            alt={a.titel}
            className="angebot-image"
          />
          <div className="angebot-actions">
            <button className="edit-button" onClick={() => setEdit(a)}>Bearbeiten</button>
            <button className="delete-button" onClick={() => handleDelete(a.id)}>Löschen</button>
          </div>
        </div>
      ))}
    </div>

    <hr className="dashboard-separator" />

    {/* 🔐 Passwort ändern */}
    <ChangePasswordForm />
  </div>
);

}
