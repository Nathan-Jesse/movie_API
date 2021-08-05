fetch("http://localhost:8080/movies", {
    method: 'POST',
    headers: {
        'Content-Type': 'applications/json'
    },
    redirect: "follow",
    body: JSON.stringify([{
        "title": "Tenet",
        "rating": "1",
        "poster": "https://m.media-amazon.com/images/M/MV5BYzg0NGM2NjAtNmIxOC00MDJmLTg5ZmYtYzM0MTE4NWE2NzlhXkEyXkFqcGdeQXVyMTA4NjE0NjEy._V1_SX300.jpg",
        "year": "2020",
        "genre": "Action, Sci-Fi",
        "director": "Christopher Nolan",
        "plot": "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
        "actors": "Elizabeth Debicki, Robert Pattinson, John David Washington, Aaron Taylor-Johnson",
        "id": 3
    }])

}).then(function (response) {
    return response.json();
}).then(function (data) {
    console.log(data);
});