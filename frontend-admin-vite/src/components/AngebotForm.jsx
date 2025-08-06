import React, { useEffect, useState } from "react";

export default function AngebotForm({ onSave, existing }) {
  const [titel, setTitel] = useState("");
  const [beschreibung, setBeschreibung] = useState("");
  const [imageBase64, setImageBase64] = useState("");

  useEffect(() => {
    if (existing) {
      setTitel(existing.titel);
      setBeschreibung(existing.beschreibung);
      setImageBase64(""); // Beim Bearbeiten kein vorhandenes Bild übernehmen
    }
  }, [existing]);

  const handleFileChange = (e) => {
  const file = e.target.files[0];
  if (!file) return;

  const img = new Image();
  const reader = new FileReader();

  reader.onload = (event) => {
    img.onload = () => {
      const canvas = document.createElement("canvas");
      canvas.width = img.width;
      canvas.height = img.height;
      const ctx = canvas.getContext("2d");
      ctx.drawImage(img, 0, 0);

      // Nur Kompression (Qualität 0.7), keine Verkleinerung
      const compressedBase64 = canvas.toDataURL("image/jpeg", 0.7);
      const base64String = compressedBase64.split(",")[1];

      console.log("📦 Komprimiertes Bild:", base64String.substring(0, 30));
      setImageBase64(base64String);
    };

    img.src = event.target.result;
  };

  reader.readAsDataURL(file);
};


  const handleSubmit = (e) => {
    e.preventDefault();
    onSave({
      titel,
      beschreibung,
      bildBase64: imageBase64, // 🟢 Korrektes Feld für das Backend
    });
    setTitel("");
    setBeschreibung("");
    setImageBase64("");
  };

  return (
    <form onSubmit={handleSubmit} className="angebot-form" >
      <h3 className="angebot-form-title">{existing ? "Angebot bearbeiten" : "Neues Angebot erstellen"}</h3>
      <input
        type="text"
        placeholder="Titel"
        value={titel}
        onChange={(e) => setTitel(e.target.value)}
        required
        className="angebot-input"
      />
      <textarea
        placeholder="Beschreibung"
        value={beschreibung}
        onChange={(e) => setBeschreibung(e.target.value)}
        required
         className="angebot-textarea"
      />
      <input type="file" accept="image/*" onChange={handleFileChange} className="angebot-file-input" />
      <button type="submit"className="angebot-submit-button">
        {existing ? "Änderungen speichern" : "Angebot hinzufügen"}
      </button>
    </form>
  );
}
