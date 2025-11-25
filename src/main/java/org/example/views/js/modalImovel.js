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

let loadingProprietaries = false;

document.querySelector(".open_modal").onclick = async () => {
    modalImovel.open();
    await optionSelectProprietary();
};

document.querySelector(".close-modal").onclick = () => {
    modalImovel.close();
};