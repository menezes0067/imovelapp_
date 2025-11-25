class Modal {
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

const modal1 = new Modal(document.querySelector('#modal-1'));

document.querySelector(".open_modal").onclick = () => {
    modal1.open();
};

document.querySelector(".close-modal").onclick = () => {
    modal1.close();
};

const modalClose = new Modal(document.querySelector('#modal-delete'));

document.querySelector(".close-modal-button").onclick = () => {
    modalClose.close();
};

document.querySelector(".execute-modal-delete").onclick = async (e) => {
    e.preventDefault();
    const id = modalClose.currentId;
    await deleteProprietary(id);
    modalClose.close();
    await showProprietaries();
};

const modalEdit = new Modal(document.querySelector('#modal-edit'));

document.querySelector(".close-modal-edit").onclick = () => {
    modalEdit.close();
};

document.querySelector(".button-update").onclick = async (e) => {
    e.preventDefault();
    const id = modalEdit.currentId;
    await updateProprietary(id);
    modalClose.close();
    await showProprietaries();
};

