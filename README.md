# LearnerApp

A JavaFX-based desktop application designed to help students track and manage their learning progress across different subjects.

## Features

- **Subject Management**: Add, edit, and delete subjects
- **Progress Tracking**: Monitor completion status for each subject
- **Topic Organization**: Organize learning materials by subjects and topics
- **Modern UI**: Clean and intuitive user interface
- **Real-time Updates**: Instant feedback on progress changes

## Prerequisites

- Java 21 or higher
- JavaFX 21
- Maven (for dependency management)

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/Lior-Karayev/LearnerApp.git
   cd LearnerApp
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn javafx:run
   ```

## Project Structure

```
src/main/java/com/learnerapp/
├── Main.java                 # Application entry point
├── controller/              # Business logic and data management
│   └── SubjectController.java
├── model/                   # Data models
│   └── Subject.java
└── view/                    # UI components
    ├── UserDashboard.java
    └── subjects/           # Subject management views
        ├── SubjectManagementView.java
        ├── SubjectCard.java
        └── AddSubjectDialog.java
```

## Usage

1. **Launch the Application**
   - Start the application using the command above
   - The main dashboard will appear with the welcome screen

2. **Manage Subjects**
   - Click "Manage Subjects" in the navigation menu
   - Add new subjects using the "+ Add Subject" button
   - View, edit, or delete existing subjects using the action buttons on each subject card

3. **Track Progress**
   - Monitor your progress through the progress bars on each subject card
   - View the number of completed topics for each subject

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- JavaFX for the UI framework
- Maven for dependency management 