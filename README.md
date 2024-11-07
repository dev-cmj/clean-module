# Clean Module Architecture Diagram

```plaintext
┌───────────────┐
│     web       │    - Presentation Layer
│  (Depends on  │      Responsible for user interface, calling business logic.
│  application, │
│    domain)    │
└───────────────┘
        ⬇️
┌───────────────┐
│  application  │    - Application Layer
│  (Depends on  │      Responsible for business logic, interacting with the domain layer.
│    domain)    │
└───────────────┘
        ⬇️
┌───────────────┐
│    domain     │    - Domain Layer
│ (Depends on   │     Includes core entity and business rules.
│    common)    │
└───────────────┘
        ⬇️
┌───────────────┐
│    common     │    - Common Layer
│ (No Depends)  │  Shared across all modules, including common utilities, constants, exceptions, etc.
└───────────────┘
