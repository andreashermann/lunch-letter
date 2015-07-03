data <- read.csv("gastwirtschaftsbetriebe_per_311214.csv", stringsAsFactors = F)
# getKMLcoordinates("doc.kml")

# E684303/N246877
data$dist <- sqrt((data$X - 684303)^2 + (data$Y - 246877)^2)
#data$dist <- abs(data$X - 684303) + abs(data$Y - 246877)

# weiter datenquellen
# http://www.lunch-card.ch/public/LunchCheck/LC_Directory.aspx?lan=de

hist(data$dist)
library(dplyr)

data.sorted <- data %>%
  arrange(dist) %>%
  select(X,Y,dist,Betriebsname)

head(data.sorted, 50)

data %>% filter(Betriebsart == "Take Away")
