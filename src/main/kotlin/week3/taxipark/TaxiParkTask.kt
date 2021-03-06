package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
    allDrivers.filter { d -> trips.all { t -> t.driver != d} }.toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
    allPassengers.filter { p -> trips.count { t -> p in t.passengers } >= minTrips }.toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
    allPassengers.filter { p -> trips.count { t -> t.driver == driver && p in t.passengers } > 1 }.toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
    allPassengers.filter { p -> trips.count { t -> p in t.passengers && t.discount != null } >
            trips.count { t -> p in t.passengers && t.discount == null } }.toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    if (trips.isEmpty()) return null

    val mostFrequentTripDuration = trips.map { it.duration }.groupBy { it / 10 }.maxBy { it.value.size }?.key
    val rangeStart = (mostFrequentTripDuration.toString() + "0").toInt()
    val rangeEnd = rangeStart + 9

    return IntRange(rangeStart, rangeEnd)
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) return false

    val totalIncome = trips.sumByDouble { it.cost }
    val top20Income = trips.groupBy { it.driver }.mapValues { (_, trips) -> trips.sumByDouble { it.cost } }
        .entries.sortedByDescending { it.value }.take(allDrivers.size / 5).sumByDouble { it.value }

    return top20Income >= totalIncome * 0.8
}