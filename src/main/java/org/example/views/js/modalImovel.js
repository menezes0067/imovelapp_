class ModalImovel {
    constructor(element) {
        this.el = element;
    }

    open() {
        this.el.showModal();
    }

    close() {
        this.el.close();
    }

    setContent(domElement) {
        this.el.appendChild(domElement);
    }
}


const modalImovel = new ModalImovel(document.querySelector('#modal-imovel'));

document.querySelector(".open_modal").onclick = async () => {
    modalImovel.open();
    await optionSelectProprietary();
};

document.querySelector(".close-modal").onclick = () => {
    modalImovel.close();
};

const modalEditImovel = new ModalImovel(document.querySelector('#modal-edit'));

document.querySelector(".close-modal-edit").onclick = () => {
    modalEditImovel.close();
};

document.querySelector(".button-update").onclick = async (e) => {
    e.preventDefault();
    const id = modalEditImovel.currentIdImovel;
    await updateImovel(id);
    await showImoveis();
    modalClose.close();
};

const modalDeleteImovel = new ModalImovel(document.querySelector('#modal-delete'));

document.querySelector(".close-modal-button").onclick = () => {
    modalDeleteImovel.close();
}

document.querySelector(".execute-modal-delete").onclick = async (e) => {
    e.preventDefault();
    const id = modalDeleteImovel.currentIdImovel;
    await deleteImovel(id);
    modalDeleteImovel.close();
    await showImoveis();
};