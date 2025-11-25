async function optionSelectProprietary() {
    const response = await fetch("http://localhost:7000/proprietario")
    const proprietaries = await response.json()

    const select = document.getElementById("selectProprietary")

    select.innerHTML = '<option value="">Selecione um proprietário</option>';

    proprietaries.forEach(p => {
       const option = document.createElement("option")
        option.value = p.id;
        option.textContent = p.nome;
        select.appendChild(option)
        console.log(option)
    })

}

document.getElementById("register-form-imovel").addEventListener("submit", async (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries());

    async function createProprietary() {
        data.disponivel = Number(data.disponivel);
        data.tipo = data.tipo.toLowerCase();
        const id = document.getElementById("selectProprietary").value;

        try {
            const res = await fetch("http://localhost:7000/imovel", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    ...data,
                    proprietario: {
                        id: Number(id)
                    }
                })
            })
            event.target.reset();
            modalImovel.close();
        }catch(err) {
            console.log('Error: ', err)
        }
    }

    await createProprietary();
})