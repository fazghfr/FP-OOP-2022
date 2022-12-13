# FP-OOP-2022

Final project PBO semester ini, saya berencana untuk membuat sebuah game. Rencana pertama saya adalah membuat game seperti game space invaders. Dimana pemain harus mengalahkan banyak musuh di langit. Musuhnya akan terus mendekat, ke pemain, dan Ketika musuh berhasil mencapai pemain, maka pemain akan kalah.  Kesimpulan, saya akan membuat Game seperti Space invaders yang sama namakan Space Defenders, dengan GUI yang akan saya usahakan pakai adalah Swing.

Penjelasan gamenya, pemain dikatakan menang jika berhasil mengalahkan semua musuhnya, dan pemain dikatakan kalah jika musuh berhasil datang ke area awal pemain.

Aspek OOP/PBO yang dipakai adalah sebagai berikut :

## 1. Casting/Conversion
Casting, salah satunya dapat dilihat pada class **gamepanel.java**, khususnya pada method paintcomponent(). Method tersebut menerima data type Graphics, sementara di dalamnya, akan ada variabel baru, yang mana variabel tersebut adalah hasil casting/conversion dari Graphics menjadi Graphics2D. Selain dari class gamepanel tersebut, terdapat pula di dalam class **highscore.java**, pada method readTopMost(), dimana dilakukan casting/parsing/conversion dari string menjadi integer dengan menggunakan method parseInt.

## 2. Constructor
Untuk constructor, salah satunya terdapat di class **player.java**, dimana pada constructor tersebut akan menerima dua argumen, yaitu gamepanel dan KeyHandler. di dalam constructor tersebut juga terdapat pemanggilan method setDefaultVal() yang akan meng-initialize variabel-variabel penting.

## 3. Overloading
Overloading disini saya gunakan pada constructor keyHandler, yang mana dalam class **KeyHandler.java** terdapat lebih dari satu constructor, namun setiap constructor menerima argumen yang berbeda.

## 4. Overriding
Untuk aspek Overriding, salah satunya terdapat pada class **highscore**, dimana pada kelas tersebut terdapat method getXCenteredStringPos(), yang mana method tersebut ada pada interface class **frame**. sehingga jika suatu class ingin menggunakan atau mendefinisikan method tersebut, maka akan dilakukan overriding untuk mengakses method tersebut. Selain itu, pada class **gamepanel**, yang meng-implement method run juga sama, karena method run() ada di interface runnable, maka jika suatu class yang menggunakannya harus melakukan overriding

## 5. Encapsulation
Encapsulation salah satunya ada di class **gamepanel**. Pada class tersebut terdapat variabel/atribut level, yang memiliki access modifiers private. sehingga, agar class lain dapat mengakses value dari level tersebut, maka perlu dilakukan encapsulation dengan getter method, yang pada class tersebut adalah getLevel()

## 6. Inheritence
Implementasi aspek inheritence terdapat pada **player.java** dimana class player adalah child class dari class sprite. sehingga atribut ataupun method yang ada di sprite dapat diakses pada class player

## 7. Polymorphism
definisi dari polymorphism ini adalah suatu objek/variabel dapat ditampilkan dalam lebih dari satu bentuk. contohnya pada **gamepanel.java**, karena class tersebut extends JPanel dan implements runnable, maka dapat dikatakan bahwa gamepanel adalah JPanel dan gamepanel adalah runnable.

## 8. ArrayList
Arraylist dipakai untuk memunculkan enemy. Jadi pada project ini, semua musuh yang ada dalam tiap level adalah objek enemy yang dikumpulkan dalam suatu arraylist of enemy dapat dilihat di **gamepanel.java**. selain itu, arraylist juga dipakai untuk menampilkan 5 skor tertinggi, yang dapat dilihat pada **highScore.java** pada method readTopFive()

## 9. Exception Handling
Karena pada project ini saya membutuhkan font, image, dan audio, maka dalam memasukkan file file tersebut saya harus melakukan exception handling. contohnya pada class **player.java**, dalam memasukkan image ke variable, perlu dilakukan dalam try-catch block untuk meng-catch IOexception.

## 10. GUI
GUI atau graphics user interface yang saya gunakan dalam project ini adalah GUI dari swing. dimana untuk method draw() atau paintComponent(), yang digunakan untuk "menggambar" menu dan gamenya didapatkan dari swing tersebut. Saya juga menggunakan JPanel dan JFrame, yang didapatkan dari GUI tersebut.

## 11. Interface
Interface yang saya gunakan, salah satunya adalah interface untuk frame yang saya namakan **frame.java**. sebenarnya penamaan ini agak kurang tepat, namun maksud saya menggunakan interface tersebut adalah supaya memudahkan setiap kali berpindah ke bagian yang berbeda. di dalamnya terdapat screenHeight, screenWidth, dan tileSize. dan juga terdapat method yang dapat dilihat pada **frame.

## 12. Abstract Class
Abstract class yang saya gunakan ada pada class **sprite.java** dimana didalamnya terdapat atribut dan declarement method.

## 13. Generics
Pada project ini, generics saya gunakan pada class **highScore.java**, dimana generics digunakan pada method readTopMost(), dua generics digunakan untuk meng-store string nama dan int skor dari top skor dari file .txt

## 14. Collection
Collection digunakan pada method sorting di **highScore.java**, pada method sortScores(), dimana dalam operasi fungsi tersebut digunakan method sort dari Collections itu sendiri

## 15. Input Output (I/O)
Untuk input output dalam project ini adalah berupa data highScore. jadi setiap kali suatu pemain mendapatkan poin, maka data tersebut akan di store di dalam file scores.txt. inputnya adalah nama dan skor dari pemain, dan outputnya adalah data highscore dalam bentuk txt, yang juga dapat ditampilkan pada menu highscore/ranks.
