In this project i used Java SpringBoot to create functioning apps with frontend written in Thymeleaf.  
For Backend queries to SQL i used Java Persistance Query Language and CrudRepositories.   
I also implemented a function to send e-mails with JavaMailSender as well as one to save the same thing to a file.  
The app uses script tags to send various CRUD requests to the Controllers.  


The website contains four Models that are related to each other in some way in the database.  
The Furniture model has a Manufacturer and is sold at various Stores, it also has a ProductCard.  
The Manufacturer model has a list of made Furniture.  
The Store model has a list of sold furniture.  
The ProductCard model is only related to one furniture.  

Users can check and add furniture to a manufacturer and select or remove furniture sold at different stores.
