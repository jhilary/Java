package ru.yandex.shad.belova.java.problem2;

import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.ReadablePeriod;

public interface Card {

    public enum OwnerType {
        Pupil, Student, Regular
    }

    public enum UsageType {
        Period, Trips, Accumulative
    }

    public enum PeriodType {
        Month{
            @Override
            public ReadablePeriod getPeriod(){
                return Months.ONE;
            }

        },
        TenDays{
            @Override
            public ReadablePeriod getPeriod(){
                return Days.days(10);
            }
        };
        public abstract ReadablePeriod getPeriod();
    }

    public enum TripsType {
        FiveTrips(5),
        TenTrips(10);

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
