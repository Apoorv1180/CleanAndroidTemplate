# Contributing to Android Clean Template

## 🚀 Getting Started

This project follows Clean Architecture + MVI pattern with Jetpack Compose and Material Design 3.

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or later
- Kotlin 2.0.21+
- Git with conventional commits

## 🌿 Git Workflow

### Branch Structure
```
main                    # Production-ready releases
├── develop            # Integration branch
├── feature/phase-1-foundation
├── feature/phase-2-features
├── feature/phase-3-advanced
├── feature/phase-4-polish
├── hotfix/critical-fixes
└── release/v1.0.0
```

### Branch Naming Convention
- **Feature branches**: `feature/phase-1-foundation`, `feature/hilt-setup`
- **Hotfix branches**: `hotfix/critical-bug-fix`
- **Release branches**: `release/v1.0.0`

### Commit Message Format
```
<type>(<scope>): <description>

[optional body]

[optional footer(s)]
```

**Types:**
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes
- `refactor`: Code refactoring
- `test`: Adding tests
- `chore`: Build process or auxiliary tool changes

**Examples:**
```
feat(phase1): add Hilt dependency injection setup
fix(ui): resolve theme preview issues
docs(readme): update installation instructions
refactor(architecture): implement Result wrapper pattern
```

## 🏗️ Development Process

### 1. Create Feature Branch
```bash
git checkout develop
git pull origin develop
git checkout -b feature/your-feature-name
```

### 2. Make Changes
- Follow the architectural rules
- Write tests for new functionality
- Update documentation if needed
- Follow coding standards

### 3. Commit Changes
```bash
git add .
git commit -m "feat(scope): your commit message"
```

### 4. Push and Create PR
```bash
git push origin feature/your-feature-name
# Create Pull Request to develop branch
```

### 5. Code Review
- Ensure all checks pass
- Address review feedback
- Update documentation if needed

### 6. Merge and Cleanup
```bash
git checkout develop
git pull origin develop
git branch -d feature/your-feature-name
```

## 📋 Phase-Based Development

### Phase 1: Foundation Setup
- [ ] Dependency injection setup (Hilt)
- [ ] Core architecture modules structure
- [ ] Design system foundation
- [ ] Navigation setup
- [ ] Error handling foundation
- [ ] Testing infrastructure

### Phase 2: Feature Implementation
- [ ] User management features
- [ ] Data persistence
- [ ] API integration
- [ ] Core business logic

### Phase 3: Advanced Features
- [ ] Offline support
- [ ] Biometrics integration
- [ ] Analytics
- [ ] Advanced UI components

### Phase 4: Performance & Polish
- [ ] Performance optimization
- [ ] Accessibility improvements
- [ ] Documentation completion
- [ ] Final testing

## 🎯 Code Standards

### Architecture Rules
- **UI Layer**: Root-Content pattern, Previews, ObserveAsEvents, No business logic
- **Domain Layer**: UseCase pattern, Domain models, Repository interfaces, State management
- **Data Layer**: Coroutines, Flow, Data source separation, Mapper organization

### Compose Guidelines
- Always include @Preview annotations with light/dark theme variants
- Use Root-Content pattern with Scaffold
- Implement proper parameter defaults and content slots
- Break complex composables into smaller functions

### Testing Requirements
- Unit tests for business logic
- UI tests for critical user flows
- Integration tests for data layer
- Test coverage > 80%

## 🏷️ Version Management

### Semantic Versioning
- `MAJOR.MINOR.PATCH`
- `v1.0.0` - Initial stable release
- `v1.1.0` - New features
- `v1.1.1` - Bug fixes

### Release Process
1. Create release branch from `develop`
2. Final testing and bug fixes
3. Update version numbers
4. Merge to `main` and tag version
5. Merge back to `develop`
6. Delete release branch

## 🚫 What NOT to Commit

- `.cursor/` directory
- `.cursorrules` file
- `local.properties`
- Build artifacts
- IDE-specific files
- Temporary files
- Sensitive data

## 📚 Resources

- [Clean Architecture Guide](https://developer.android.com/topic/architecture)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)

## 🤝 Getting Help

- Create an issue for bugs or feature requests
- Use discussions for questions
- Follow the code of conduct
- Be respectful and constructive

---

**Happy Coding! 🚀**
