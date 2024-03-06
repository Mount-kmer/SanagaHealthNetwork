const header2Template = document.createElement("template");
header2Template.innerHTML =    `
    
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@100&family=Roboto+Serif:wght@100&family=Roboto+Slab:wght@100&family=Rubik:wght@300&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/1346609601.js" crossorigin="anonymous"></script>
    
    <style>
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.topnav {
  overflow: hidden;
  background-color: lightsteelblue;
}

.topnav a {
  float: left;
  margin-left: 5rem;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  /* background-color: ; */
  color: white;
}

.topnav .icon {
  display: none;
}

img{
    width: 40px;
    position: relative;
    height: 30px;
    top: .5rem;
    background-color: white;
    /* top: 1.5rem; */
}

@media screen and (max-width: 600px) {
  .topnav a:not(:first-child) {display: none;}
  .topnav a.icon {
    float: right;
    display: block;
  }
}

@media screen and (max-width: 600px) {
  .topnav.responsive {position: relative;}
  .topnav.responsive .icon {
    position: absolute;
    right: 0;
    top: 0;
  }
  .topnav.responsive a {
    float: none;
    display: block;
    text-align: left;
 
  }
  .active{
    display: none;
  }
  .topnav a {
    margin-left: 1rem;
  }
}
</style>
</head>

 <h2 style="text-align: center;"><img src="static/images/doctor-img.jpeg"><a href="/user-home" style="text-decoration: none; color: black;">SanagaHealth</a> </h2>

<div class="topnav" id="myTopnav">
  <a href="/search" class="active"><i class="fa fa-fw fa-search"></i>Search providers</a>
  <!-- <a href="#news">News</a> -->
  <a href="#contact"><i class="fa fa-address-card"></i> Contact</a>
  <a href="#about"><i class="fa fa-heart"></i> About</a>
  <a href="javascript:void(0);" class="icon">
    <i class="fa fa-bars"></i>
  </a>
</div>

`

class Header2 extends HTMLElement {
    constructor() {
        super();
        this.closeNav = this.closeNav.bind(this)

    }
     closeNav() {
        let x = document.getElementById("myTopnav");
        if (x.className === "topnav") {
            x.className += " responsive";
        } else {
            x.className = "topnav";
        }
    }

    connectedCallback() {
        const shadowRoot = this.attachShadow({ mode: 'open' });
        shadowRoot.appendChild(header2Template.content.cloneNode(true));

        const icon = shadowRoot.querySelector('.icon');
        icon.addEventListener('click', this.closeNav);
    }
}
document.addEventListener("DOMContentLoaded", function (){
    customElements.define("header2-component",Header2)
})
