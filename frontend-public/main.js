document.addEventListener("DOMContentLoaded", () => {
  const container = document.getElementById("angebote-container");
  const status = document.getElementById("status");

  fetch("http://localhost:8080/api/angebote")
    .then(response => response.json())
    .then(angebote => {
      status.remove();
      console.log("Angebote geladen:", angebote); // 👈 Testausgabe
      angebote.forEach(angebot => {
        const card = document.createElement("div");
        card.className = "angebot";
        card.innerHTML = `
          <img src="${angebot.imageUrl}" alt="${angebot.titel}" />
          <div class="angebot-content">
            <h3>${angebot.titel}</h3>
            <p>${angebot.description}</p>
          </div>
        `;
        container.appendChild(card);
      });
    })
    .catch(err => {
      console.error("Fehler beim Laden der Angebote:", err);
      status.textContent = "Fehler beim Laden der Angebote";
    });
});
