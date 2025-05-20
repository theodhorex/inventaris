document.getElementById("genre").addEventListener("change", movie);
const movieAction = ["Avengers", "John Wick 4", "Gladiator"];
const movieDrama = ["The Covenant", "Dune: Part Two", "The Bikeriders"];

function movie() {
  var genre = document.getElementById("genre").value;
  var movieSelect = document.getElementById("movie");
  movieSelect.innerHTML = "";
  if (genre === "Action") {
    movieAction.forEach(function (movie) {
      const option = document.createElement("option");
      option.value = movie;
      option.textContent = movie;
      movieSelect.appendChild(option);
    });
  } else if (genre === "Drama") {
    movieDrama.forEach(function (movie) {
      const option = document.createElement("option");
      option.value = movie;
      option.textContent = movie;
      movieSelect.appendChild(option);
    });
  } else if (genre === "Comedy") {
    document.getElementsByTagName("p")[0].innerHTML =
      "Maaf, Belum ada film untuk genre ini";
  } else if (genre == "") {
    const option = document.createElement("option");
    option.value = "";
    option.textContent = Select;
    movieSelect.appendChild(option);
  }
}

function makeRegistration() {
  var nama = document.getElementById("name").value;
  var email = document.getElementById("email").value;
  var tanggal = document.getElementById("date").value;
  var genre = document.getElementById("genre").value;
  var movie = document.getElementById("movie").value;
  if (
    nama != "" &&
    email != "" &&
    tanggal != "" &&
    genre != "" &&
    movie != ""
  ) {
    alert(
      `Reservation Successfully made!\n Nama = ${nama} \n Email = ${email} \n Tanggal = ${tanggal}\n Genre = ${genre} \n Movie = ${movie}`
    );
    document.getElementById("cinemaForm").reset();
  }
}
