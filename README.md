# ez-colet
Colhon Bogdan-Nicolae, grupa 2.1  
Drăgoi Ovidiu, grupa 2.2  
  
&emsp;Proiectul nostru urmărește rezolvarea problemei lipsei unei ore exacte a livrării coletelor 
prin curier. Problema apare atunci când un pachet urmează a fi livrat într-o anumită zi, dar cel 
care îl primește, știe doar că pachetul se va livra în acea zi, sau într-un interval orar mult prea
mare pentru a putea fi într-adevăr considerat util.  
  
&emsp;Prin aplicația noastră, un client va putea urmări în timp real și va putea aproxima mult
mai bine ora la care se va face livrarea.  

&emsp;Aplicația va fi una pentru telefoanele mobile. În acest scop, vom folosi mediul de 
dezvoltare Android Studio, și limbajul Kotlin, iar pentru stocarea informațiilor vom folosi
Firebase.  

&emsp;Aplicația se va deschide cu o fereastră de Login și de creare a unui cont. Această aplicație
va fi folosită atât de către curieri cât și de către clienți, fiecare având interfețe diferite. În cazul în 
care un user alege să își creeze un cont, i se va cere să își introducă numele, prenumele, adresa, 
un username și o parola. Toate acestea se vor stoca într-o baza de date. Contul nou creat va primi 
calitatea de client.  

&emsp;Un client nu va putea să își creeze un cont de curier. Conturile de curieri sunt create
manual în baza de date. La angajare, un curier primește username-ul și parola cu care se va putea 
conecta direct la aplicație.  

&emsp;Pentru conectare, clientului i se va cere doar username-ul și parola

**Curierul**  
&emsp;&emsp;- Va avea un buton de scanat coduri QR. Atunci când un cod QR este scanat, se vor extrage 
informațiile despre colet, cum ar fi: numele, prenumele și strada clientului, și se vor 
adaugă în lista cu livrări pe ziua respectivă.  
&emsp;&emsp;- Poate să șteargă o intrare din listă, cum ar fi situația în care a scanat de două ori același
cod.  
&emsp;&emsp;- Poate să modifice ordinea din listă în funcție de cererile de prioritate respectiv amânare
ale clienților.  
&emsp;&emsp;- Poate confirma finalizarea unei livrări.  
&emsp;&emsp;- I se va salva numărul comenzilor finalizate într-o luna si numărul total al acestora.  

**Client**  
&emsp;&emsp;- Va vedea o listă cu livrările pe care le va executa în ziua aceea curierul la care este 
repartizat, cât și locul acestuia în listă.  
&emsp;&emsp;- Va vedea ora la care s-a finalizat fiecare livrare.  
&emsp;&emsp;- Poate cere livrare cu prioritate.  
&emsp;&emsp;- Poate cere livrare în altă zi.  
&emsp;&emsp;- Poate să ofere o notă curierului.  

&emsp;Legătura dintre un client și un colet se va realiza prin informațiile oferite de contul unui 
client și informațiile extrase din codul QR. De aceea, când un client își creează un cont pe 
aplicație, va trebui să introducă cat mai multe date.  

&emsp;Prin faptul că un client poate să vadă la ce număr se află în lista cu livrări cât și ora la care 
livrările de dinaintea lui s-au finalizat, el va putea aproxima mult mai bine ora la care îi va 
ajunge pachetul.  
