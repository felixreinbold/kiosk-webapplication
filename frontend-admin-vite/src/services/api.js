
import { getToken } from "../utils/auth";
const API_BASE = "http://localhost:8080/api";



export async function getAngebote() {
  const res = await fetch(`${API_BASE}/angebote`);
  if (!res.ok) throw new Error("Fehler beim Abrufen");
  return res.json();
}

export async function createAngebot(angebot) {
  const res = await fetch(`${API_BASE}/angebote`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
    body: JSON.stringify(angebot),
  });
  if (!res.ok) throw new Error("Fehler beim Erstellen");
}

export async function updateAngebot(id, angebot) {
  const res = await fetch(`${API_BASE}/angebote/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
    body: JSON.stringify(angebot),
  });
  if (!res.ok) throw new Error("Fehler beim Aktualisieren");
}

export async function deleteAngebot(id) {
  const res = await fetch(`${API_BASE}/angebote/${id}`, {
    method: "DELETE",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
  });
  if (!res.ok) throw new Error("Fehler beim Löschen");
}

export async function changePassword(oldPassword, newPassword) {
  const res = await fetch("http://localhost:8080/auth/change-password", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + getToken(),
    },
    body: JSON.stringify({ oldPassword, newPassword }),
  });

  if (!res.ok) {
    throw new Error("Fehler beim Ändern des Passworts");
  }
}

export async function resetPassword() {
  const res = await fetch("http://localhost:8080/auth/reset", {
    method: "POST",
  });

  if (!res.ok) {
    throw new Error("Reset fehlgeschlagen");
  }

  return res.text();
}
