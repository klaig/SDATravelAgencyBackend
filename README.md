# SDATravelAgencyBackend

This repository is the backend part of SDA's final project, a dynamic travel agency system designed to streamline the browsing, "purchasing," and management of travel packages.

## Features
- **Tour Management**: Admins can manage tour details.
- **Robust Search**: Users can filter tours by various parameters.
- **Secure Purchase Process**: Features a secure transaction system for booking tours.
- **Data Validation**: Ensures data integrity.

## Security
- **JWT Authentication**: Implements JWT for secure authentication.
- **Spring Security**: Used for authorization and access control.

## Technologies
- Spring
- Hibernate
- Angular

## Usage
### Authentication
- **Sign In**: POST to `/api/auth/signin` with `LoginDto`.
- **Sign Up**: POST to `/api/auth/signup`.

### Tour Management (Admin)
- **Create Tour**: POST to `/api/admin/tour/create`.
- **Update/Delete Tour**: PUT/DELETE to `/api/admin/tours/{tourId}`.

### Tour Browsing and Booking
- Use GET endpoints under `/api/v1` to search for tours.
- Book tours via relevant booking endpoints.

### Access Control
- Public endpoints accessible without authentication. Others require a valid JWT token.
