package ru.yandex.shad.belova.java.problem2;

import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.ReadablePeriod;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/12/13
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */

interface Card {

    enum OwnerType {
        Pupil, Student, Regular
    }

    enum UsageType {
        Period, Trips, Accumulative
    }

    enum PeriodType {
        Month{
            @Override
            ReadablePeriod getPeriod(){
                return Months.ONE;
            }

        },
        TenDays{
            @Override
            ReadablePeriod getPeriod(){
                return Days.days(10);
            }
        };
        abstract ReadablePeriod getPeriod();
    }

    public enum TripsType {
        FiveTrips(5),
        TenTrips(10);

        int numTrips;

        TripsType(int numTrips) {
            this.numTrips = numTrips;
        }

        int getNumTrips() {
            return numTrips;
        }
    }

    String getID();
    OwnerType getOwnerType();
    UsageType getUsageType();
    boolean pay();

    AggregatedCardInfo getCardInfo();


}
