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

