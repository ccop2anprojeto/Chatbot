<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE>
        <html lang="pt-BR">

        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="description" content="A Kathbot é uma iniciativa privada para sei lá o que -- seus lixo">
            <title>Kathbot</title>
            <link rel="shortcut icon" type="image/png" href="assets/images/unicorn.png" />
            <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-" crossorigin="anonymous">
            <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
            <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
            <link rel="stylesheet" href="assets/styles/master.css">
        </head>

        <body>
            <section class="header">
                <nav class="navbar home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
                    <a class="nav-logo pure-menu-heading" href="">Kathbot</a>
                    <ul class="nav-list pure-menu-list">
                        <li class="nav-item pure-menu-item">
                            <a href="#" class="pure-menu-link">Home</a>
                        </li>
                        <li class="nav-item pure-menu-item">
                            <a href="#" id="nav-btn" class="pure-menu-link">Sobre</a>
                        </li>
                        <li class="nav-item pure-menu-item">
                            <a href="#" id="nav-btn" class="pure-menu-link">Disponibilidade</a>
                        </li>
                        <li class="nav-item pure-menu-item">
                            <a href="#" id="nav-btn" class="pure-menu-link">Tecnologia</a>
                        </li>
                        <li class="nav-item pure-menu-item">
                            <a href="login.html" class="pure-menu-link">Login</a>
                        </li>
                    </ul>
                </nav>
            </section>
            <div class="container menu-home">
                <div class="icon_main">
                    <a class="nav-logo pure-menu-heading menu-mobile" href="">Kathbot</a>
                    <a href="#">
                        <i class="fa fa-bars"></i>
                    </a>
                </div>
                <div class="overlay"></div>
                <section class="column_main">
                    <nav class="nav_main">
                        <div class="box_info">
                            <div class="icon_closeMain">
                                <a href="#">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <ul>
                            <li>
                                <a id="nav-btn" href="#">Home</a>
                            </li>
                            <li>
                                <a id="nav-btn" href="#">Sobre</a>
                            </li>
                            <li>
                                <a id="nav-btn" href="#">Disponibilidade</a>
                            </li>
                            <li>
                                <a id="nav-btn" href="#">Tecnologia</a>
                            </li>
                        </ul>
                    </nav>
                </section>
            </div>

            <section class="container-header">
                <div class="hero">
                    <h1 class="hero-head">Por um mundo sem filas</h1>
                    <p class="hero-subhead">Com a
                        <strong class="hero-subhead-strong">Kathbot</strong>, você não precisa mais enfrentar filas para ser atendido no cartório!</p>
                </div>
            </section>

            <section id="elementtoScrollToID" class="content">
                <h2 class="content-head is-center">Sobre</h2>
                <div class="information pure-g">
                    <div class="pure-u-1 is-left">
                        <div class="l-box">
                            <p class="about">O
                                <b>Kathbot</b> é um sistema de chatbot desenvolvido com o intuito de fornecer ao cidadão acesso
                                às informações para os atos de cada especialidade de cartório. Os serviços notariais e de
                                registro são exercidos em caráter privado, por pessoas formadas, após aprovação em concurso
                                público de provas e títulos. Ou seja, cartório não passa de pai para filho. O concurso público
                                para titular de cartório é promovido pelo Tribunal de Justiça do Estado, sendo que maiores
                                informações poderão ser obtidas no Portal do Extrajudicial.
                            </p>
                        </div>
                    </div>
                </div>
            </section>

            <section class="container-header-2">
                <div class="hero"></div>
            </section>

            <section class="content">
                <h2 class="content-head is-center">Disponibilidade</h2>
                <div class="information pure-g">
                    <div class="pure-u-1 is-left">
                        <div class="l-box">
                            <p class="about"> Não cobramos pelo serviço fornecido diretamente do informado, recebemos direto do governo, preço
                                decido pelo Poder judicial de acordo com a constituição federal por isso temos o maior prazer
                                em fornecer informações importantes a população.
                            </p>
                        </div>
                    </div>
                </div>
            </section>

            <section class="quote">
                <p>
                    <i class="fas fa-quote-left"></i> "O Kathbot é simples, fácil e rápido" - João Ricardo, 28.</p>
            </section>

            <section class="content">
                <h2 class="content-head is-center">O que é um chatbot?</h2>
                <div class="information pure-g">
                    <div class="pure-u-1 is-left">
                        <div class="l-box">
                            <p class="about">Um Chatbot é um programa de computador que tenta simular um ser humano na conversação com as
                                pessoas. O objetivo é responder as perguntas de tal forma que as pessoas tenham a impressão
                                de estar conversando com outra pessoa e não com um programa de computador. Após o envio de
                                perguntas em linguagem natural, o programa consulta uma base de conhecimento e em seguida
                                fornece uma resposta que tenta imitar o comportamento humano. E é exatamente isso que o
                                <b>Kathbot</b> é!
                            </p>
                        </div>
                    </div>
                </div>
            </section>

            <section class="container-header-3">
                <div class="hero"></div>
            </section>
            <div class="container_chat">
                <header>
                    <span>Atendimento Online</span>
                    <!-- <span>Maria Pereira</span>
                <span class="item_id">ID: 4X8383</span>-->
                    <div class="icon_close">
                        <a href="#">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </header>
                <div class="content_messages">
                    <div class="box_view">
                        <!-- <div class="message received">
                        <span></span>
                    </div>
                    
                    <div class="message sent">
                        <span></span>
                    </div>      -->
                    </div>
                </div>
                <div class="content_area">
                    <div class="box_area">
                        <input class="input-box" id="perg" name="pergunta" placeholder="Digite aqui..." />
                        <div class="main_options">
                            <button class="btn btn-primary btn-enviar" id="sendMsg">Enviar</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="wisper">
                <h2>Bem-vindo ao atendimento online!</h2>
                <!-- <h2>Clique em começar para iniciar o atendimento.</h2> -->
                <div class="area-botao">
                    <a class="btn-comecar" href="#">Começar</a>
                </div>
            </div>
            <div class="icon_chat active">
                <a href="#">
                    <i class="fa fa-comment fa-2x"></i>
                </a>
            </div>

            <footer class="footer">
                <figure class="kathbot-logo">
                    <img src="../Chatbot/assets/images/kathbot-logo.png" alt="Logo da Kathbot">
                    <figcaption>Kathbot</figcaption>
                </figure>
                <div class="footer-box">
                    <h2>Onde Estamos:</h2>
                    <p>
                        <i class="fas fa-map-pin"></i> Av. Brigadeiro Faria Lima, 878</p>
                    <p>
                        <i class="fas fa-envelope"></i> E-mail: atendimento@kathbot.com </p>
                    <p>
                        <i class="fas fa-mobile-alt"></i> Telefone: (11) 5011-9923</p>
                </div>
                <div class="footer-box">
                    <h2>Siga-nos:</h2>
                    <div class="social-box">
                        <i class="icon fab fa-facebook-f"></i>
                        <i class="icon fab fa-twitter"></i>
                        <i class="icon fab fa-linkedin-in"></i>
                    </div>
                </div>
                <div class="end-footer">
                    <p>Kathbot 2018 &copy; Copyright - All Rights Reserved</p>
                </div>
            </footer>
        </body>
        <script src="assets/lib/jquery-3.3.1.min.js"></script>
        <script src="assets/scripts/funcionario.js"></script>
        <script src="assets/scripts/main.js"></script>
        <script src="assets/scripts/navscroll.js"></script>

        </html>