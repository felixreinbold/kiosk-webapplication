Die Kiosk-Webapplikation ist eine vereinfachte Weblösung, mit der ein Veranstalter einfach, schnell und ohne technische Kenntnisse neue Veranstaltungen auf seiner Webseite veröffentlichen kann.
Die Veranstaltungen werden in einer Datenbank mit Bildern persistiert.
Über ein geschütztes Admin-Dashboard können Events erstellt, bearbeitet oder gelöscht werden, während die öffentliche Webseite automatisch alle veröffentlichten Veranstaltungen anzeigt.

Der Name Kiosk stammt aus meiner Ursprünglichen Idee weil ein bekannter einen Kisok besitzt bei dem er gelegentlich Angebote für besondere Spezialitäten macht.

Diese Applikation kann von jedem veranstalter implementiert werden und mit wenig technischem Aufwand auf seiner Homepage einsetzen.

🔐 Admin-Bereich
Sicherer Login über JWT-Authentifizierung
Veranstaltungen erstellen, bearbeiten und lösche
Bilder zu Veranstaltungen hochladen (falls aktiviert)
Passwort ändern
„Passwort vergessen“-Funktion mit Einmal-Reset-Key

🌐 Öffentliche Webseite
Listet alle sichtbaren Events
Detailseiten für jede Veranstaltung
Events werden per API aus dem Backend geladen

Backend (Spring Boot)
Java 17
Spring Boot
Spring Security + JWT
PostgreSQL
JPA/Hibernate
REST-API

Frontend
React (Admin-Frontend)
HTML/CSS/JavaScript (Öffentliches Frontend)
Axios für API-Kommunikation
JWT-Handling im Admin-Frontend
