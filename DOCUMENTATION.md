# LearnerApp Documentation

## Overview
LearnerApp is a JavaFX application for managing learning subjects and their topics. It provides a user-friendly interface for tracking progress and managing educational content.

## Class Structure

### Models
- `Subject`: Represents a learning subject with properties like name, progress, and topic counts
- `Topic`: Represents a topic within a subject, including status and video content

### Controllers
- `SubjectController`: Manages subject data and operations (add, delete, update)
- `TopicController`: Manages topic data and operations for each subject

### Views
#### Subject Management
- `SubjectManagementView`: Main view for managing subjects
- `SubjectCard`: Card component displaying subject information and actions
- `AddSubjectDialog`: Dialog for adding new subjects

#### Topic Management
- `TopicView`: View for displaying and managing topics within a subject

## Class Details

### Subject
- **Purpose**: Represents a learning subject
- **Properties**:
  - `name`: Subject name
  - `progress`: Completion percentage
  - `totalTopics`: Total number of topics
  - `completedTopics`: Number of completed topics
  - `subjectId`: Unique identifier

### Topic
- **Purpose**: Represents a topic within a subject
- **Properties**:
  - `id`: Unique identifier
  - `subjectId`: Reference to parent subject
  - `name`: Topic name
  - `status`: Completion status (Completed, In Progress, Not Started)
  - `videoUrl`: URL for video content

### SubjectController
- **Purpose**: Manages subject data and operations
- **Key Methods**:
  - `getSubjectList()`: Returns list of all subjects
  - `addSubject(Subject)`: Adds a new subject
  - `deleteSubject(Subject)`: Removes a subject
  - `updateSubject(Subject, Subject)`: Updates subject details

### TopicController
- **Purpose**: Manages topic data and operations
- **Key Methods**:
  - `getTopicListForSubject(int)`: Returns topics for a specific subject
  - `addTopic(Topic)`: Adds a new topic
  - `updateTopicStatus(Topic, String)`: Updates topic status

### SubjectManagementView
- **Purpose**: Main view for subject management
- **Features**:
  - Grid layout of subject cards
  - Add subject functionality
  - Subject progress tracking

### SubjectCard
- **Purpose**: Displays subject information and actions
- **Features**:
  - Subject name and progress
  - View topics button
  - Edit and delete actions
  - Progress bar visualization

### TopicView
- **Purpose**: Displays and manages topics for a subject
- **Features**:
  - Topic list with status indicators
  - Progress tracking
  - Watch course functionality
  - Coming soon section

## UI Components
- Uses JavaFX for UI
- Implements Material Design icons
- Responsive grid layout
- Custom styling with CSS

## Data Management
- Currently uses in-memory storage
- Prepared for future database integration
- Subject-Topic relationship maintained through IDs

## Future Improvements
- Database integration
- User authentication
- Video content management
- Progress analytics
- Mobile responsiveness 