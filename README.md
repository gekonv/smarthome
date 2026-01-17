# Smart Home Simulation

A complex Java application designed to simulate the daily operations of a smart household. The system models the interactions between inhabitants (humans, pets), appliances, and sensors while tracking resource consumption, handling asynchronous events, and generating detailed activity reports.

This project focuses heavily on Object-Oriented Design and the implementation of various Design Patterns.

## Features

* **Complex Entity Hierarchy:** Simulation of a complete house structure (House -> Floors -> Rooms -> Windows/Devices).
* **Life Simulation:** Inhabitants perform actions based on a 50/50 ratio between using appliances and sporting activities (Skiing, Biking).
* **Event-Driven Architecture:** Sensors generate events handled by appropriate observers (e.g., Wind Sensor triggers blinds to close).
* **Resource Management:** Tracks electricity, gas, and water consumption across different device states (Idle, Active, Off).
* **Device Lifecycle:** Devices wear down over time, can break, and require repair using "Lazy-loaded" manuals.
* **Reporting System:** Generates comprehensive text reports regarding configuration, events, usage, and financial costs.

## Design Patterns Implemented

The core requirement of this project was to utilize specific software design patterns to solve architectural problems:

| Pattern | Implementation |
| :--- | :--- |
| **Composite** | Used to represent the house hierarchy. Allows treating the House, Floor, and Room objects uniformly when generating configuration reports. |
| **Observer** | Handling sensor events. *Example: When a Wind Sensor detects strong wind, it notifies all Window Blinds in the room to retract.* |
| **State** | Manages the lifecycle of devices. Devices transition between states (Active, Idle, Broken, Off) based on usage and events. |
| **Builder** | Used to construct custom Rooms and their contents based on the provided JSON configuration. |
| **Factory** | Encapsulates the creation logic for different types of appliances and devices. |
| **Visitor** | Traverses the Composite structure to calculate stats and generate the `ActivityAndUsageReport` without modifying the entity classes. |
| **Lazy Initialization** | Optimization for documentation. Device repair manuals are large objects and are only loaded into memory when a device actually breaks and needs repair. |
| **Stream API** | Java Streams are utilized for efficient searching within the Composite structure (e.g., finding a room by name). |

## Configuration

The simulation is configured via a JSON file. This allows dynamic setup of the house layout, inhabitants, and initial devices without recompiling code.

### Example `config.json` structure:
```json
{
  "house": {
    "id": 1
  },
  "floors": [
    {
      "level": 0
    },
    {
      "level": 1
    },
    {
      "level": 2
    }
  ],
  "rooms": [
    {
      "floor": 0,
      "name" : "kitchen",
      "windows": 2,
      "devices": ["FEEDER", "FEEDER", "FRIDGE", "WASHING_MACHINE"]
    },
    {
      "floor": 0,
      "name" : "living room",
      "windows": 3,
      "devices": ["FEEDER", "FRIDGE", "PC"]
    },
    {
      "floor": 1,
      "name" : "kitchen 1",
      "windows": 3,
      "devices": ["TV", "FEEDER", "MICROWAVE", "DRIER"]
    }
  ],
  "people": [
    {
      "room": "kitchen",
      "name": "Pepa",
      "adult": "yes"
    },
    {
      "room": "kitchen",
      "name": "Franta",
      "adult": "no"
    }
  ],
  "pets": [
    {
      "room": "kitchen",
      "name": "Snow",
      "type": "CAT"
    }
  ],
  "equipment": [
    {
      "type": "bike"
    },
    {
      "type": "skateboard"
    },
    {
      "type": "bike"
    }
  ]
}
```
## Output Reports

Upon completion, the application generates four detailed text reports:

* **HouseConfigurationReport.txt**: A hierarchical view of the house structure and all inhabitants.

* **EventReport.txt**: A chronological log of all events, grouped by type, source, and handler.

* **ActivityAndUsageReport.txt**: Statistics on inhabitant activities (who used what) and device usage frequency.

* **ConsumptionReport.txt**: Detailed breakdown of resource usage (kW/h, liters, m3) and financial costs.

## Tech Stack

* **Language**: Java
* **Build System**: Maven
* **Data Format**: JSON
* **Documentation**: Javadoc
