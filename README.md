# Apparel Discount Calculator
Calculates the discounted price on apparels. Apparels can have discounts at brand, category or ancestor category level. The discount that is applied is the greatest of the above three. 
Please check the Requirement section for complete information.

## Getting Started
### Pre-requisites
```
Java 8
Maven 3
```
Set the environment variables JAVA_HOME, MAVEN_HOME to point to the corresponding location and add these to the path variable.
#### Sample commands for Windows
```
set JAVA_HOME=C:\Progra~1\Java\jdk1.8.0_71
set MAVEN_HOME=D:\apache-maven-3.3.9
set path=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%path%;
```
###Installation
Download the code to local machine and run the following command to build the application.
```
mvn package
```


### How to run the application
Application accepts command line arguments to accept the above files and few more configurations. Below is a summary of the command line arguments.
```
usage: discount-calculator
 -b,--brands-and-discounts <arg>          Brands and discounts file. If this field is not provided then
                                          default brand discount file will be used.
 -c,--categories-and-discounts <arg>      Categories and discounts File. If this field is not provided
                                          then default category discount file will be used.
 -f,--overwrite-output-file-if-required   Overwrite output file if it already exists
 -o,--orders <arg>                        Order file
 -p,--products <arg>                      Product catalogue File
 -t,--target-file <arg>                   Output file with the discounted prices. If this field is not
                                          provided then defaults to a file named discounted-prices.txt in
                                          current directory
```
Below is a sample usage
```
java -jar target\apparel-discount-calculator-0.0.1-SNAPSHOT.jar -o src/test/resources/order.csv -p src/test/resources/product-catalogue.csv -f
```

### Input and output files
#### Order File
```
2
2
1,2,3,4
1,5
```

#### Product Catalogue File
```
5
1, Arrow,Shirts,800
2, Vero Moda,Dresses,1400
3, Provogue,Footwear,1800
4, Wrangler,Jeans,2200
5, UCB,Shirts,1500
```

#### Brands And Discounts File
```
#brand, discount percentage
Wrangler,10
Arrow,20
Vero Moda,60
UCB,0
Adidas,5
Provogue,20
```

#### Categories and Discounts File
```
#id, name, parentId, discount percentage
# Men's Wear
1, Men's Wear, 0, 0
2, Shirts, 1, 0
3, Trousers, 1, 0
4, Casuals, 3, 30
5, Jeans, 3, 20
# Women's Wear
6, Women's wear, 0, 50
7, Dresses, 6, 0
8, Footwear, 6, 0
```

#### Discounted Prices File
```
560
3860
2140
```

## References
### Requirement
Discounts on apparel

This shopping season you are having fun at the Mall. The Mall owner, himself, is quite stressed out having to manage the influx of customers.
He is struggling to calculate the discounts that he has on his clothing line. You decide to help him out by building a system that calculates the discounts on all the applicable items a customer has bought.
 
There are several categories of products. In fact, categories have subcategories which themselves can have subcategories. Below is a diagram.
Casuals is a subcategory of Trousers, which by itself is a subcategory of Men's wear. Some categories have discounts.
 
            Men's wear                 Women's wear (50% off)
            |- Shirts                          	|- Dresses
            |- Trousers                    		|- Footwear
				|- Casuals (30% off)
				|- Jeans   (20% off)
 
Each product you have belongs to a brand which by themselves are running discounts. Below is a table that lists them:
Brands Discounts:
Wrangler             10%
Arrow                   20%
Vero Moda        60%
UCB                       None
Adidas                  5%
Provogue            20%
 
This way, a product can have three types of discounts applicable:
1. Discount on the brand
2. Discount on the category
3. Discount on the ancestor category (e.g. Footwear doesn't have a discount, but it's parent category Women's wear has 50% off). It is worth noting, that it is an ancestor: not just a direct parent, anyone in the lineage.
 
The discount that is applied is the greatest of the above three. For example, if the customer buys a Jeans of Wrangler Brand, the discounts are:
1. Discount on brand: 10%
2. Discount on category (Jeans): 20%
3. Discount on parents (Trousers, Men's wear): None
So, the discount that is applied 20%.
 
```
Inventory (the list of items that shop has):
------------------------------------------------------------------------
Id | Brand            | Category          | Price   | Discounted Price |
------------------------------------------------------------------------
1  | Arrow            | Shirts            | 800     | 640              |
2  | Vero Moda        | Dresses           | 1400    | 560              |
3  | Provogue         | Footwear          | 1800    | 900              |
4  | Wrangler         | Jeans             | 2200    | 1760             | 
5  | UCB              | Shirts            | 1500    | 1500             |
```
 
You will be given the above table (without discounted price) in CSV form as standard input. This is the shop inventory.
You'll also get the customer options as comma separated Id's after a newline. In the example below, 1,2,3,4 are the customer choices.
``` 
Sample Input:
 
5
1, Arrow,Shirts,800
2, Vero Moda,Dresses,1400
3, Provogue,Footwear,1800
4, Wrangler,Jeans,2200
5, UCB,Shirts,1500
```
``` 
2
1,2,3,4
1,5
```
```
Expected output:
3860
2140
```
