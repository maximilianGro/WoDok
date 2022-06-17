export interface Appointment {
  id: number;
  freeAppointment: boolean;
  practitionerId: number;
  patientId: number;
  start: Date;
  end: Date;
  patientDescription: string;
}
