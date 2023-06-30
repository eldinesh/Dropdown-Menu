# DropDown Menu

This test is written in Java using Selenium WebDriver. It can be imported in any IDE. It is tested on Chrome and Firefox browsers.


## Prerequisites

  Before you begin, make sure you have the following installed on your machine:

- Java JDK 8
- Slenium 4.10.0 or higher
- WebDriver (Chrome or Firefox) 



## Getting Started

1. Clone the repository to your local machine:

```terminal
git clone https://github.com/YOUR_USERNAME/Dropdown-Menu.git.git -b main
```

2. Add the required packages:

```terminal
add selenium.jar as external library to your project
```

3. Place Chrome Driver or Firefox Driver in "Drivers" folder under project directory
  
4. Set your parameter as "chrome" or "firefox" in driver function

4. Run the project


## Detailed Test Description: 
```terminal
Launch the browser.
Open the URL: https://mail.rediff.com/cgi-bin/login.cgi
Click on "Create a new account link"
Enter the name as “Kamal” and Rediff mail as “kamal1234” and click on “Check Availability” button.
Select one of the auto suggested mail options
Enter password as “Kamal@1234”.
Select the Checkbox “Click if you don't have an alternate ID”
Select the Date of Birth “20-Jun-2000”
Click on Country dropdown list box
Fetch all the available country names and display on console
Print the total count of countries.
Select Country with Visible Text=”India” 
Print the name of country selected on console
Validate the selected country against the expected i.e, India
Close the browser
```

## License

This project is licensed under the Apache License 2.0. See the LICENSE file for details.
