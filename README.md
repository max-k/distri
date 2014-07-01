distri : Generic Java Distributor
---------------------------------

This application is based on swing and require a functionnal X server.

Quick start
===========

```.bash
$ make all
$ java -jar distri.jar
```

Build instructions
==================

```.bash
Makefile for IPRO project `distri`

Usage :
make             Build .class files
make jar         Build .jar file
make doc         Build documentation
make all         Build classes, jar and documentation
make clean       Remove .class files
make mrproper    Remove all build directories
```

Usage instructions
==================

There is no command-line arguments.

To launch the program, you need to use this command :

```.bash
$ java -jar distri.jar
```

Customize your distributor
==========================

This distributor can handle any kind of product of money and any kind of product.

Some of them are akready implemented, other aren't.

The default one uses a Drink class as a product and a Euro as a money.

Classes Diagram
===============

### GenericDistributorGui
Generic GUI for any kind of distributor

### GenericDistributor
Can handle any kind of product and any kind of money

Composed of two founds to hold money and a container to hold products

### EuroDrinkDistributor
Specialized distributor used to sold drinks in Euro

Derived from GenericDistributor

### Main
Used to choose wich kind of distributor to use

Also spawns GUI

### Found
Generic found to store any kind of money

### Container
Generic container to store any kind of product

### Product
Base class for all kinds of products

### Drink
Specialized class to manae drinks

Derived from Product

### Money
Base class for all kinds of money

### Euro
Specialized class to manage Euro

Derived from Money

### Dollar
Specialized class to manage Dollar

Derived from Money

Implementation details
======================

List of existing coins are stored in classes derived from Money

List of existing products are stored in classes derived from GenericDistributor

Customized distributor must be statically spawned from Main class

Distributors cannot be field yet. To set startup quantities you need to edit
your class derived from GenericDistributor

