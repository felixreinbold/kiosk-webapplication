export function saveToken(token) {
  localStorage.setItem("token", token);
  const expiry = Date.now() + 15 * 60 * 1000; // 15 Minuten ab jetzt
  localStorage.setItem("tokenExpiry", expiry.toString());
}

export function getToken() {
  return localStorage.getItem("token");

}

export function isLoggedIn() {
  const token = getToken();
  const expiry = localStorage.getItem("tokenExpiry");

  if (!token || !expiry) return false;
  return Date.now() < parseInt(expiry, 10);
}


export function logout() {
  localStorage.removeItem("token");
  localStorage.removeItem("tokenExpiry");
}
