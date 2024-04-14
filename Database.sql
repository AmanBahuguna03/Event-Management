

use event_management;

CREATE TABLE events (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    event_date DATE NOT NULL,
    event_time TIME NOT NULL,
    venue VARCHAR(100) NOT NULL
);  

CREATE TABLE attendees (
    attendee_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    attendee_name VARCHAR(100) NOT NULL,
    attendee_email VARCHAR(100),
    FOREIGN KEY (event_id) REFERENCES events(event_id)
);

CREATE TABLE schedules (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    schedule_date DATE NOT NULL,
    schedule_time TIME NOT NULL,
    description TEXT,
    FOREIGN KEY (event_id) REFERENCES events(event_id)
);

select * from events;
