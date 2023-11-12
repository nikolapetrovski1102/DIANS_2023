import pandas as pd
from geopy.geocoders import Nominatim

data = pd.read_csv('starter_data.csv')  # Читање на .csv file за филтрирање

data = data.drop(data
                 .filter(
    regex='nodes/|members/|int_name|operator|grape_variety|name:en|name:mk|layer|type|crop')  # Ги трга сите колони кои се состојат во regex
                 .columns,
                 axis=1)

geolocator = Nominatim(user_agent="location_geocoder")
this_locations = []


def pipeAndFilter(data_to_filter):
    # Прва голема буква во келијата на tags/landuse
    data_to_filter['tags/landuse'] = data_to_filter['tags/landuse'].str.capitalize()
    # Ако во келијата tags/name нема никаква вредност тогаш во tags/landuse стави вредност Winery
    data_to_filter.loc[data['tags/name'].notna(), 'tags/landuse'] = 'Winery'
    # Ако во tags/name нема никаквва вредност тогаш вредноста да биде Vineyard Surface
    data_to_filter.loc[data['tags/name'].isna(), 'tags/name'] = 'Vineyard Surface'


def get_location_name(data_locations):
    for i in range(0, len(data_locations)):
        # Го зима секој lat во избраниот .csv file
        lat = data_locations['center/lat'][i]
        # Го зима секој lon во избраниот .csv file
        lon = data_locations['center/lon'][i]
        # Ја наоѓа локацијата со помош на екстензијата GeoPy
        location = geolocator.reverse((lat, lon), language='en')
        # Ако адресата не постои во тој ред на келијата за локација да стои празно поле
        if location.address is None:
            this_locations.append(None)
        # Ако адресата постои ја става локацијата во келијата на моменталниот lat & lon
        else:
            this_locations.append(location.address)


# Повик на функцијата
get_location_name(data)
pipeAndFilter(data)
# Ги сметсува сите локација во колоната на .csv file-от
data["locations"] = this_locations
# Ново создадениот file се именува во updated_data.csv
data.to_csv('updated_data.csv', index=False)
