https://trello.com/b/O8bukVgG/travel-agency

Service for a travel agency
Brief description of the system

As part of the project, a system should be created that allows searching for trips according to given criteria and allow for their "purchase".
Main system functions

    Home
    Setting up the tour offer (administrator)
    Search for tours by given criteria
    "Purchase" the tour - calculation of the final amount according to the number of people

Technologies

    Spring + Hibernate + Angular

Basic Entities:

Continent

    name
    
Country

    name
    continent membership (foreign key)

City

    name
    nationality (foreign key)

Tour

    where to (City)
    departure date
    date of return
    number of days
    price for an adult
    price for a child
    promoted
    number of seats

Purchasing a tour

    tour
    data of participants
    amount

Functionalities
Home

    presentation of promoted trips
    presentation of upcoming tours (globally)
    presentation of upcoming trips (divided into continents)
    presentation of upcoming trips (by country)
    presentation of recently purchased tours
    (optional) presentation of tours with a reduced price (you need to add a mechanism for this)
    (optional) presentation of tours with only 1 or 2 places left

Home page - 2

    each of the lists below should present, at least, 3 entries + a link where the user can see more and be directed to search results according to a given criterion, e.g. clicking the Tenerife link should redirect us to the page with trips to Tenerife
    continent, country, city, hotel should be clickable and lead to the search results
    after clicking on a specific tour, detailed information is presented
    (optional) under the tour, we present tours to the same place, but with a later date
    (optional) under the tour we present tours to other hotels in this city
    (optional) we present trips to other cities from this country under the tour

Configuring the tour offer

    the administrator (on a separate page) has the ability to add and edit tours
    the form should allow you to enter all the parameters of the trip
    you need to pre-configure the database of continents, countries, cities, airports and hotels
    (optional) separate pages for managing continents, countries, cities, airports and hotels

Search for trips based on given criteria

    all clickable elements (continents, countries, cities, hotels are directed to the search results page

    additionally, the website has a form that allows for filtering and sorting results

    you can search for tours by (e.g.):
        city (airport) of departure
        city (hotel) of stay
        departure date (optional range)
        date of return (optional range)
        type (BB, HB, FB, AI)
        a hotel by it's standard
        amount of days

    you can sort by (e.g.):
        price
        departure date

    (optional) As the amount of data grows, you can introduce pagination of the search results

Purchasing a tour

    after choosing a specific trip, it can be purchased
    specify the number of adults and children
    if there are enough vacancies, the tour will be "purchased"
    the number of vacancies will be reduced
    the amount for the trip will be calculated (based on the number of people)
    purchased tours are displayed on the administration page / pages
    (optional) you can group the above-mentioned tours and enter a simple search engine

Additional requirements

    it is necessary to ensure an aesthetic and functional way of presenting data
    data downloaded from users should be pre-validated
