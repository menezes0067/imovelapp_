document.getElementById("register-form").addEventListener("submit", async (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries());

    async function createProprietary() {
        data.idade = Number(data.idade);

        try {
            const res = await fetch("http://localhost:7000/proprietario", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
            event.target.reset();
            modal1.close();
        }catch(err) {
            console.log('Error: ', err)
        }
    }
    await createProprietary();
    await showProprietaries();

})

async function getProprietaries(){
    const response = await fetch("http://localhost:7000/proprietario")
    const proprietaries = await response.json()

    return proprietaries
}

async function deleteProprietary(id) {
    const response = await fetch(`http://localhost:7000/proprietario/${id}`, {
        method: "DELETE",
    })
    if(!response.ok) {
        throw new Error("Erro ao deletar usuário");
    }
    console.log("Usuário deletado com sucesso");
}

async function showProprietaries(){
    const proprietaries = await getProprietaries()

    let table = document.querySelector('.proprietary-information')
    table.innerHTML = ''

    proprietaries.forEach(proprietary => {
        table.appendChild(renderTableRow(proprietary))
    })
}

function renderTableRow(proprietary){
    let tr = document.createElement("tr")
    let tds = []

    let tdName = document.createElement("td");
    tdName.innerText = proprietary.nome

    let tdCpf = document.createElement("td");
    tdCpf.innerText = proprietary.cpf

    let tdEmail = document.createElement("td");
    tdEmail.innerText = proprietary.email

    let tdIdade = document.createElement("td");
    tdIdade.innerText = proprietary.idade

    tr.appendChild(tdName);
    tr.appendChild(tdCpf);
    tr.appendChild(tdEmail);
    tr.appendChild(tdIdade);

    tr.appendChild(editOnCloseButton(proprietary.id))
    return tr
}

window.onload = showProprietaries()

let currentId = null

function editOnCloseButton(id) {
    let tdActions = document.createElement("td");

    let editButton = document.createElement("button");
    editButton.innerText = "✏️";
    editButton.classList.add("edit-button");

    let deleteButton = document.createElement("button");
    deleteButton.innerText = "🗑️";
    deleteButton.classList.add("open-modal-delete", "delete-button");

    deleteButton.dataset.id = id;


    deleteButton.addEventListener("click", () => {
        modalClose.currentId = id;
        modalClose.open();
    });

    tdActions.appendChild(editButton);
    tdActions.appendChild(deleteButton);

    return tdActions;
}



