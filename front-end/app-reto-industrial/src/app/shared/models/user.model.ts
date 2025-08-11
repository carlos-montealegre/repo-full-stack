export interface User {
  id: number;
  username: string;
  email: string;
  password?: string; // opcional para no exponerla en el frontend
  roles: string[];
}
