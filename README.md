# Home Insulation Rating Application (Ecobee Challenge Test)

## Getting started
1. Ensure maven is installed [run: `brew install mvnvm`]
2. Unzip the contents the ecobee-rating zip file.
3. Open terminal, navigate to project directory using the 'cd' cmd (/ecobeerating), run the following commands:
- [run: `mvn clean install`]
- [run: `java -jar -Dapple.awt.UIElement="true" target/ecobee-rating-1.0-SNAPSHOT.jar -h`]

## Task Purpose
In this task, you are to write a program that will rate the ecobee thermostat owners by the quality of insulation of their home. Each user's R-value is to be rated relative to the others in the same region (city/province/county) they live.
The rating is a number between 1 and 10. For a given home and region, if the percentage of homes with better insulation (R-value) is in the [90, 100) range, then the home is rated 1, if the percentage of homes with better insulation is in the [80, 90) range, then the home is rated 2. A home that is in the top range, such that percentage of homes with better insulation falls in [0, 10) range, it is rated 10.
 

## Task Requirements
The input data is provided on stdin and the program should write to stdout. 

The Input consists of two sections, a data section followed by an empty line, followed by a query section.

The data section is a sequence of lines in the following format:
`"<name>" "<location>" <rvalue>`
`"<name>" "<location>" <rvalue>`
...
where:
*	`<name>` is the customer's name
*	`<location>` is the location of the building and has the following format: <country>/<state>/<city>
*	`<rvalue>` is R-value for the building, a number between 0.0 and 50.0, with at most three digits after the decimal point.
The lines in the data section are not in any particular order.
The query section is a sequence of lines containing pairs of name and region, one pair on each line. You are to calculate the rating for each pair in the query section. The format is:
`"<name>" "<region>"`
`"<name>" "<region>"`
...
where:
*	`name` is customer's name
*	`region` can be one of `<country>`, `<country>/<province>` or `<country/province/city>`

The output should contain the same number of lines as the query section of the input, in the following format:
`"<name>" "<region>" "<score>"`
where:
*	`name` is customer name
*	`region` is a region in which the customer is scored
*	`score` a number from 1 to 10 that represents the rating of the building in that region.
The output should be in the same order as appears in the query section of the input.
Sample input:<br/>
"John Doe" "Canada/Ontario/Toronto" 1.5<br/>
"Samanta Smith" "Canada/Ontario/London" 3.7<br/>
"Adam Xin" "Canada/British Columbia/Vancouver" 2.110<br/>
"Monica Taylor" "Canada/Ontario/Toronto" 2.110<br/>
"Alicia Yazzie" "US/Arizona/Phoenix" 5.532<br/>
"Mohammed Zadeh" "Canada/Ontario/Toronto" 1.43<br/>

"John Doe" "Canada"<br/>
"John Doe" "Canada/Ontario"<br/>
"Alicia Yazzie" "US/Arizona"<br/>
    
## How to Test
- [run `mvn test`]
