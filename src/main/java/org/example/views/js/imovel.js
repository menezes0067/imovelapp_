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

    async function createImovel() {
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

    await createImovel();
    await showImoveis();
})

async function getImoveis(){
    const response = await fetch("http://localhost:7000/imovel")
    const proprietaries = await response.json()

    return proprietaries
}

async function showImoveis(){
    const imoveis = await getImoveis()

    let table = document.querySelector('.imovel-information')
    table.innerHTML = ''

    imoveis.forEach(imovel => {
        table.appendChild(renderTableRow(imovel))
    })
}

async function deleteImovel(id) {
    try {
        const res = await fetch(`http://localhost:7000/imovel/${id}`, {
            method: "DELETE",
        });

        if (!res.ok) {
            const errorText = await res.text(); // opcional, pega mensagem do backend
            throw new Error(errorText || "Erro ao deletar imovel");
        }

    } catch (err) {
        console.error("Erro ao deletar:", err);
        alert("Não foi possível deletar o imóvel. " + err.message);
    }
}

function renderTableRow(imovel){
    let tr = document.createElement("tr")
    let tds = []

    let tdName = document.createElement("td");
    tdName.innerText = imovel.nome

    let tdDescricao = document.createElement("td");
    tdDescricao.innerText = imovel.descricao

    let tdTipo = document.createElement("td");
    tdTipo.innerText = imovel.tipo

    let tdDisponivel = document.createElement("td");
    tdDisponivel.innerText = imovel.disponivel ? "Sim" : "Não";

    let tdProprietarionome = document.createElement("td");
    tdProprietarionome.innerText = imovel.proprietario.nome

    console.log(tdProprietarionome)

    tr.appendChild(tdName);
    tr.appendChild(tdDescricao);
    tr.appendChild(tdTipo);
    tr.appendChild(tdDisponivel);
    tr.appendChild(tdProprietarionome);
    tr.appendChild(editOnCloseButton(imovel.id, imovel))

    return tr
}

window.onload = showImoveis();

let currentIdImovel = null

function editOnCloseButton(id, imovel) {

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
        modalDeleteImovel.currentIdImovel = id;
        modalDeleteImovel.open();
    });


    editButton.addEventListener("click", () => {
        modalEditImovel.currentIdImovel = id;
        modalEditImovel.open();

        sessionStorage.setItem("editImovel", JSON.stringify(imovel));
        autoInformationImovel();

    });

    tdActions.appendChild(editButton);
    tdActions.appendChild(deleteButton);

    return tdActions;
}

async function updateImovel(id) {
    const form = document.getElementById("edit-form");

    const formData = new FormData(form);
    const data = {};

    formData.forEach((value, key) => {
        if (!isNaN(value) && value.trim() !== "") {
            data[key] = Number(value);
        } else {
            data[key] = value;
        }
    });

    if (data.disponivel !== undefined) {
        data.disponivel = data.disponivel == 1 ? 1 : 0;
    }

    console.log("DATA ENVIADO:", data);


    try {
        const res = await fetch(`http://localhost:7000/imovel/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        if (!res.ok) {
            console.log("Erro ao atualizar");
            return;
        }

        form.reset();

        document.getElementById("modal-edit").close();

    } catch (err) {
        console.log("Erro:", err);
    }
}

function autoInformationImovel() {
    const imovel = JSON.parse(sessionStorage.getItem("editImovel"));
    if (!imovel) return;

    const fields = document.querySelectorAll("#edit-form [name]");

    fields.forEach(field => {
        const name = field.name;

        if (field.tagName.toLowerCase() === "textarea") {
            field.value = imovel[name] || "";
        }

        else if (field.tagName.toLowerCase() === "select") {
            if (typeof imovel[name] === "boolean") {
                field.value = imovel[name] ? "1" : "0";
            } else {
                field.value = imovel[name];
            }
        }
    });
}
