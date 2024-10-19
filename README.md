# ProcessMonitor

**ProcessMonitor** is a lightweight yet powerful tool designed to monitor the **Resident Set Size (RSS)** and **CPU usage** of specified processes on your PC. It efficiently records this data into a **SQLite database** and provides the capability to visualize the collected metrics through interactive **charts and diagrams** using HCharts.

## 🚀 Features

- **Real-Time Monitoring:** Track the RSS and CPU usage of any selected processes on your system in real-time.
- **Data Persistence:** Securely store all collected metrics in a SQLite database for future analysis and reporting.
- **Interactive Visualization:** Generate dynamic charts and HCharts to vividly display the resource usage of your processes.
- **User-Friendly Interface:** Intuitive operation and clear visualizations make monitoring and analysis effortless.
- **Lightweight and Efficient:** Minimal resource consumption ensures that **ProcessMonitor** does not burden your system.

## 📦 Installation

### Prerequisites

- **Java 8** or higher
- **SQLite** installed on your system

### Steps

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/ProcessMonitor.git
   ```
2. **Navigate to the Directory:**
   ```bash
   cd ProcessMonitor
   ```
3. **Install Dependencies:**
   Ensure that all necessary libraries and dependencies are installed. If you're using Maven or Gradle, you can use the following commands:
   - **Maven:**
     ```bash
     mvn install
     ```
   - **Gradle:**
     ```bash
     gradle build
     ```
4. **Run the Application:**
   ```bash
   java -jar ProcessMonitor.jar
   ```

## 🛠️ Usage

1. **Select Processes:**
   Choose the processes you want to monitor by entering their names or PIDs.
2. **Start Monitoring:**
   Click the **Start** button to begin real-time monitoring. RSS and CPU data will be continuously collected and stored in the SQLite database.
3. **Visualize Data:**
   Navigate to the dashboard to analyze the collected data through interactive charts and HCharts. Customize the visualizations as needed and export the graphs for reports or presentations.

## 📈 Visualization Example

![Sample Chart](path/to/your/screenshot.png)

*Figure 1: Interactive chart displaying the CPU usage of a process over time.*

## 🗃️ Data Model

**ProcessMonitor** utilizes a structured SQLite database to store monitoring data. The data model consists of two main tables:

### 1. `process`

Stores basic information about each monitored process.

- **id** INTEGER PRIMARY KEY AUTOINCREMENT
- **pid** INTEGER NOT NULL
- **name** TEXT
- **command_line** TEXT
- **start_time** DATETIME

### 2. `process_metrics`

Stores RSS and CPU metrics for each process at various timestamps.

- **id** INTEGER PRIMARY KEY AUTOINCREMENT
- **process_id** INTEGER NOT NULL (Foreign key referencing `process(id)`)
- **timestamp** DATETIME NOT NULL
- **rss** INTEGER NOT NULL
- **cpu_usage** REAL NOT NULL

## 🔧 Development

### Architecture

**ProcessMonitor** is built with a modular architecture to facilitate easy extension and maintenance. The main components include:

- **Data Acquisition:** Continuously collects RSS and CPU data from selected processes.
- **Database Management:** Handles the SQLite database, ensuring efficient storage and retrieval of data.
- **Visualization:** Creates interactive charts and HCharts to visually represent the collected data.
- **User Interface:** Provides an intuitive interface for selecting processes, controlling monitoring, and viewing visualizations.

### Creating Tables

The application initializes the necessary database tables upon startup. Below is the method responsible for creating the tables:

```java
private void create_tables() {
    // SQL statements to create tables
    String createProcessTable = "CREATE TABLE IF NOT EXISTS process (
"
            + "    id INTEGER PRIMARY KEY AUTOINCREMENT,
"
            + "    pid INTEGER NOT NULL,
"
            + "    name TEXT,
"
            + "    command_line TEXT,
"
            + "    start_time DATETIME
"
            + ");";

    String createProcessMetricsTable = "CREATE TABLE IF NOT EXISTS process_metrics (
"
            + "    id INTEGER PRIMARY KEY AUTOINCREMENT,
"
            + "    process_id INTEGER NOT NULL,
"
            + "    timestamp DATETIME NOT NULL,
"
            + "    rss INTEGER NOT NULL,
"
            + "    cpu_usage REAL NOT NULL,
"
            + "    FOREIGN KEY (process_id) REFERENCES process(id)
"
            + ");";

    // Optional: Create indexes to improve query performance
    String createIndexProcessPid = "CREATE INDEX IF NOT EXISTS idx_process_pid ON process(pid);";
    String createIndexMetricsProcessId = "CREATE INDEX IF NOT EXISTS idx_metrics_process_id ON process_metrics(process_id);";
    String createIndexMetricsTimestamp = "CREATE INDEX IF NOT EXISTS idx_metrics_timestamp ON process_metrics(timestamp);";

    try (Statement stmt = conn.createStatement()) {
        // Create tables
        stmt.execute(createProcessTable);
        LOGGER.info("Executing statement: {}", createProcessTable);

        stmt.execute(createProcessMetricsTable);
        LOGGER.info("Executing statement: {}", createProcessMetricsTable);

        // Create indexes
        stmt.execute(createIndexProcessPid);
        LOGGER.info("Executing statement: {}", createIndexProcessPid);

        stmt.execute(createIndexMetricsProcessId);
        LOGGER.info("Executing statement: {}", createIndexMetricsProcessId);

        stmt.execute(createIndexMetricsTimestamp);
        LOGGER.info("Executing statement: {}", createIndexMetricsTimestamp);

    } catch (SQLException e) {
        LOGGER.error("Error while creating Tables: {}", e.getMessage());
    }
}
```

## 📝 License

This project is licensed under the [MIT License](LICENSE).

## 🤝 Acknowledgements

- **Open-Source Community:** For providing numerous helpful libraries and tools.
- **SQLite:** For the reliable and efficient database solution.
- **Java Developers:** For the powerful and versatile programming language.

---

With **ProcessMonitor**, you have a reliable tool at your disposal to keep an eye on the resource usage of your processes, enabling informed decisions to optimize your system. Whether for personal projects or professional applications, **ProcessMonitor** offers the flexibility and performance you need.

If you have any questions or need support, feel free to create an [Issue](https://github.com/your-username/ProcessMonitor/issues) or contact me directly!

---

**Note:** Remember to replace placeholders like `https://github.com/your-username/ProcessMonitor.git` and `path/to/your/screenshot.png` with the actual URLs and paths relevant to your project.
