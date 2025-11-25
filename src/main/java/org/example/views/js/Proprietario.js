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

async function updateProprietary(id) {
    const inputs = document.querySelectorAll("#edit-form [name]");

    const data = {};
    inputs.forEach(input => {
        data[input.name] = input.type === "number"
            ? Number(input.value)
            : input.value;
    });

    try {
        const res = await fetch(`http://localhost:7000/proprietario/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        inputs.forEach(i => i.value = "");

        modalEdit.close();
    } catch (err) {
        console.log("Erro:", err);
    }
}

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

    let tdTelefone = document.createElement("td");
    tdTelefone.innerText = proprietary.telefone

    tr.appendChild(tdName);
    tr.appendChild(tdCpf);
    tr.appendChild(tdEmail);
    tr.appendChild(tdIdade);
    tr.appendChild(tdTelefone)

    tr.appendChild(editOnCloseButton(proprietary.id, proprietary))
    return tr
}

window.onload = showProprietaries()

let currentId = null

function editOnCloseButton(id, proprietary) {

    let tdActions = document.createElement("td");

    let editButton = document.createElement("button");
    editButton.innerText = "✏️";
    editButton.classList.add("open-modal-edit", "edit-button");

    editButton.dataset.id = id;

    let deleteButton = document.createElement("button");
    deleteButton.innerText = "🗑️";
    deleteButton.classList.add("open-modal-delete", "delete-button");

    deleteButton.dataset.id = id;

    deleteButton.addEventListener("click", () => {
        modalClose.currentId = id;
        modalClose.open();
    });


    editButton.addEventListener("click", () => {
        modalEdit.currentId = id;
        sessionStorage.setItem("editUser", JSON.stringify(proprietary))
        modalEdit.open();
        autoInformationProprietary()
    });

    tdActions.appendChild(editButton);
    tdActions.appendChild(deleteButton);

    return tdActions;
}

function autoInformationProprietary() {
    console.log(sessionStorage.getItem("editUser"));
    const user = JSON.parse(sessionStorage.getItem("editUser"));
    if (!user) return;

    const inputs = document.querySelectorAll("#edit-form [name]");

    inputs.forEach(input => {
        input.value = user[input.name]
    });
}