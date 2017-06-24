function redirectFunction() {
    return setTimeout(
        function() {
            window.location = "http://localhost:8080/services/main";
        }, 5);
}
