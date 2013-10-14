package ru.yandex.shad.belova.java.problem2;

import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.ReadablePeriod;

public interface Card {

    public interface Type{}
    public enum OwnerType implements Type{
        PUPIL, STUDENT, REGULAR
    }

    public enum UsageType implements Type{
        PERIOD, TRIPS, ACCUMULATIVE
    }

    public enum PeriodType {
        MONTH {
            @Override
            public ReadablePeriod getPeriod(){
                return Months.ONE;
            }

        },
        TEN_DAYS {
            @Override
            public ReadablePeriod getPeriod(){
                return Days.days(10);
            }
        };
        public abstract ReadablePeriod getPeriod();
    }

    public enum TripsType {
        FIVE_TRIPS(5),
        TEN_TRIPS(10);

        private int numTrips;

        TripsType(int numTrips) {
            this.numTrips = numTrips;
        }

        public int getNumTrips() {
            return numTrips;
        }
    }

    String getID();
    OwnerType getOwnerType();
    UsageType getUsageType();
    boolean pay();
}
