const headerTemplate = document.createElement('template');
headerTemplate.innerHTML = `
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@100&family=Roboto+Serif:wght@100&family=Roboto+Slab:wght@100&family=Rubik:wght@300&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/1346609601.js" crossorigin="anonymous"></script>
<style>
    .sanagaTopNav{
        overflow:  hidden;
        background: cornflowerblue;  
        
    }
    
    .sanagaTopNav a {
   
      /*float: left;*/
      margin-left: 10rem;
      display: inline-flex;
      color: #f2f2f2;
      /*text-align: center;*/
      padding: 14px 16px;
      text-decoration: none;
      font-size: 17px;
    }
    
    .sanagaTopNav a:hover{
      background-color: cornflowerblue;
      color: black;
    }
    
    .sanagaTopNav a.active{
         color: white;
    }
    
    img{
    width: 40px;
    position: relative;
    height: 30px;
    top: .5rem;
    background-color: white;
    /* top: 1.5rem; */
}
    
    .sanagaTopNav .icon{
        display: none;
    }
    
    @media screen and (max-width: 600px) {
        .sanagaTopNav a:not(:first-child){display: none;}
        .sanagaTopNav a.icon {
            float: right;
            display: block;
        }
        
        .sanagaTopNav a{
         margin-left: 1rem;
         padding: 10px 12px;
        }
        h2{
            margin-bottom: 20px;
        }
    
    }
    
    @media screen and (max-width: 600px){
        .sanagaTopNav.responsive {position: relative}
        .sanagaTopNav.responsive a.icon{
            position: absolute;
            right: 0;
            top: 0;
        }
        .sanagaTopNav.responsive a {
            float: none;
            display: block;
            text-align: left;
        }
    }
    
  
       
</style>
 <h2 style="text-align: center; margin-bottom: 20px">
     <img src="../images/doctor-img.jpeg" alt="company logo"/>
     <a href="/user-home" style="text-decoration: none; color: black;">SanagaHealth</a> 
 </h2>
 
<div class="sanagaTopNav" id="mySanagaNav">
<a href="/user-home" class="active"><i class="fa fa-home"></i></i> Home</a>
 <a href="/search" class="active"><i class="fa fa-fw fa-search"></i> Search providers</a>
  <a href="#contact"><i class="fa fa-address-card"></i> Contact</a>
  <a href="#about"><i class="fa fa-heart"></i> About</a>
   <a href="javascript:void(0);" class="icon" ><i class="fa fa-bars"></i></a>
</div>
  
`

class Header extends HTMLElement {
    constructor() {
        // Always call super first in constructor
        super();
        this.collapseNav = this.collapseNav.bind(this);
    }

     collapseNav(){
        let nav = this.shadowRoot.getElementById("mySanagaNav")
        if (nav.className === "sanagaTopNav"){
            nav.className += " responsive";
        } else {
            nav.className = "sanagaTopNav";
        }
    }

    connectedCallback() {
        const shadowRoot = this.attachShadow({ mode: 'open' });
        shadowRoot.appendChild(headerTemplate.content.cloneNode(true));

        const icon = shadowRoot.querySelector('.icon');
        icon.addEventListener('click', this.collapseNav);
    }
}

customElements.define('header-component', Header);
