export default class AnimatedNavbar {

    constructor (navId,buttonId){
        this.navbar = document.getElementById(navId);
        this.button = document.getElementById(buttonId);
        this.status = 'closed';
        this.lgWidth = '20%';
        this.smWidth = '70%';
        this.breakpoint = 768;
        this.init();
    }

    init () {
        this.button.addEventListener('click', () => {this.action();});
    }

    action (){ 
        if (this.status == 'closed') {
            this.open();
            this.changeButtonToClose();
        }else{
            this.close();
            this.changeButtonToOpen();
        }
    }

    open () {

        if(this.viewportWidthObtain() < this.breakpoint){
            this.navbar.style.width = this.smWidth;
        }else{
            this.navbar.style.width = this.lgWidth;
        }

        this.status = 'open';
    }

    close () {
        this.navbar.style.width = '0%';
        this.status = 'closed';
    }

    changeButtonToOpen () {
        this.button.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/></svg>';
    }

    changeButtonToClose () {
        this.button.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16"><path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/><path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/></svg>';
    }

    viewportWidthObtain(){
        return document.documentElement.clientWidth;
    }

}