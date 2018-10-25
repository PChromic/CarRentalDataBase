package com;

import com.pchromic.Enum.WorkerPosition;

public class WorkerSearchCriteria {

    private Long officeId;

    private Long carId;

    private WorkerPosition workerPosition;

    public WorkerSearchCriteria() {
    }

    private WorkerSearchCriteria(Long officeId, Long carId, WorkerPosition workerPosition) {
        this.officeId = officeId;
        this.carId = carId;
        this.workerPosition = workerPosition;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public WorkerPosition getWorkerPosition() {
        return workerPosition;
    }

    public void setWorkerPosition(WorkerPosition workerPosition) {
        this.workerPosition = workerPosition;
    }

    public static WorkerSearchCriteria.WorkerSearchCriteriaBuilder builder() {
        return new WorkerSearchCriteria.WorkerSearchCriteriaBuilder();
    }

    public static class WorkerSearchCriteriaBuilder {
        private Long officeId;

        private Long carId;

        private WorkerPosition workerPosition;

        WorkerSearchCriteriaBuilder() {
            super();
        }

        public WorkerSearchCriteriaBuilder withOfficeId(long officeId) {
            this.officeId = officeId;
            return this;
        }
        public WorkerSearchCriteriaBuilder withCarId(long carId) {
            this.carId = carId;
            return this;
        }

        public WorkerSearchCriteriaBuilder withWorkerPosition(WorkerPosition workerPosition) {
            this.workerPosition = workerPosition;
            return this;
        }
        public WorkerSearchCriteria build() {
            return new WorkerSearchCriteria(officeId, carId, workerPosition);
        }


    }
}
