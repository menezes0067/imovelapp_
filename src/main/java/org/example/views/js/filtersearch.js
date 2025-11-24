const searchInput = document.querySelector("#search-input");
const tbody = document.querySelector(".proprietary-information");
const clearButtonfilter = document.querySelector(".clear-btn");

searchInput.addEventListener("input", () => {
    const filter = searchInput.value.toLowerCase();

    const rows = tbody.querySelectorAll("tr");

    rows.forEach(row => {
        const text = row.innerText.toLowerCase();

        if (text.includes(filter)) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }

        clearButtonfilter.addEventListener("click", () => {
            row.style.display = "";
            searchInput.value = "";
        })
    });
});


