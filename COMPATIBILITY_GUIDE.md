# 🔄 **LIKE-TO-LIKE COMPATIBILITY GUIDE**

## 📋 **OVERVIEW**

This guide demonstrates how our Android Clean Template provides **like-to-like compatibility** with both RUNIQUE and Screenly while maintaining our superior implementations.

## 🎯 **COMPATIBILITY STRATEGY**

### **✅ What We Maintain:**
1. **Superior Result Wrapper**: More comprehensive than both projects
2. **Better Error Handling**: Most detailed DataError types
3. **Enhanced Testing**: Most complete testing infrastructure
4. **20-Item Checklist**: Comprehensive standards

### **🔄 What We Adapt:**
1. **Repository Patterns**: Support both simple (RUNIQUE) and UseCase (Screenly) patterns
2. **ViewModel Patterns**: Support both simple and MVI patterns
3. **Data Flow**: Maintain compatibility with existing project structures

---

## 🏗️ **REPOSITORY PATTERN COMPATIBILITY**

### **RUNIQUE Style (Simple Repository):**
```kotlin
// RUNIQUE: Direct repository injection into ViewModel
class AnalyticsDashboardViewModel @Inject constructor(
    private val analyticsRepository: AnalyticsRepository
) : SimpleViewModel<AnalyticsDashboardState>() {
    init {
        viewModelScope.launch {
            val result = analyticsRepository.getAnalyticsValues()
            if (result is Result.Success) {
                setState(result.data.toAnalyticsDashboardState())
            }
        }
    }
}
```

### **Screenly Style (UseCase + Repository):**
```kotlin
// Screenly: UseCase pattern with repository
class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : ParameterizedUseCase<List<Movie>, Int>() {
    override suspend operator fun invoke(input: Int): Result<List<Movie>, DataError> {
        return movieRepository.getPopularMovies(input)
    }
}

class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : MviViewModel<MovieListState, MovieListAction, MovieListEvent>() {
    override fun onAction(action: MovieListAction) {
        when (action) {
            MovieListAction.LoadMovies -> loadMovies()
        }
    }
}
```

### **Our Hybrid Style (Best of Both):**
```kotlin
// Our Template: Hybrid approach supporting both patterns
class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : HybridUseCase<List<Movie>, Int>() {
    override suspend operator fun invoke(input: Int = 1): Result<List<Movie>, DataError> {
        return movieRepository.getPopularMovies(input)
    }
}

class HybridMovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val movieRepository: MovieRepository
) : HybridViewModel<MovieState, MovieAction, MovieEvent>() {
    // Can use both patterns
    init {
        // RUNIQUE style: Direct repository call
        viewModelScope.launch {
            val result = movieRepository.getPopularMovies(1)
            // Handle result...
        }
    }
    
    override fun onAction(action: MovieAction) {
        when (action) {
            MovieAction.LoadMovies -> loadMovies() // Screenly style: UseCase call
        }
    }
}
```

---

## 📊 **DATA FLOW COMPARISON**

### **RUNIQUE Data Flow:**
```
Repository → ViewModel (init block) → UI State
```

### **Screenly Data Flow:**
```
Repository → UseCase → ViewModel (onAction) → UI State + Events
```

### **Our Template Data Flow:**
```
Repository → UseCase (optional) → ViewModel (hybrid) → UI State + Events
```

---

## 🔧 **IMPLEMENTATION EXAMPLES**

### **1. Simple UseCase (RUNIQUE Compatible):**
```kotlin
class GetAnalyticsUseCase @Inject constructor(
    private val analyticsRepository: AnalyticsRepository
) : SimpleUseCase<AnalyticsValues>() {
    override suspend operator fun invoke(): Result<AnalyticsValues, DataError> {
        return analyticsRepository.getAnalyticsValues()
    }
}
```

### **2. Parameterized UseCase (Screenly Compatible):**
```kotlin
class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : ParameterizedUseCase<List<Movie>, Int>() {
    override suspend operator fun invoke(input: Int): Result<List<Movie>, DataError> {
        return movieRepository.getPopularMovies(input)
    }
}
```

### **3. Hybrid UseCase (Our Enhanced):**
```kotlin
class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : HybridUseCase<List<Movie>, Int>() {
    override suspend operator fun invoke(input: Int = 1): Result<List<Movie>, DataError> {
        return movieRepository.getPopularMovies(input)
    }
}
```

---

## 🎨 **VIEWMODEL PATTERNS**

### **1. Simple ViewModel (RUNIQUE Compatible):**
```kotlin
class AnalyticsDashboardViewModel @Inject constructor(
    private val analyticsRepository: AnalyticsRepository
) : SimpleViewModel<AnalyticsDashboardState>() {
    init {
        viewModelScope.launch {
            val result = analyticsRepository.getAnalyticsValues()
            if (result is Result.Success) {
                setState(result.data.toAnalyticsDashboardState())
            }
        }
    }
}
```

### **2. MVI ViewModel (Screenly Compatible):**
```kotlin
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : MviViewModel<MovieListState, MovieListAction, MovieListEvent>() {
    override fun onAction(action: MovieListAction) {
        when (action) {
            MovieListAction.LoadMovies -> loadMovies()
        }
    }
}
```

### **3. Hybrid ViewModel (Our Enhanced):**
```kotlin
class HybridMovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val movieRepository: MovieRepository
) : HybridViewModel<MovieState, MovieAction, MovieEvent>() {
    // Supports both patterns
}
```

---

## 🚀 **MIGRATION STRATEGY**

### **From RUNIQUE to Our Template:**
1. **Keep existing repository patterns** ✅
2. **Add UseCase layer gradually** (optional)
3. **Upgrade to enhanced Result wrapper** ✅
4. **Add comprehensive error handling** ✅

### **From Screenly to Our Template:**
1. **Keep existing UseCase patterns** ✅
2. **Upgrade to enhanced Result wrapper** ✅
3. **Add comprehensive error handling** ✅
4. **Add enhanced testing infrastructure** ✅

### **New Projects with Our Template:**
1. **Use hybrid approach** for maximum flexibility
2. **Follow 20-item checklist** for consistency
3. **Leverage superior implementations** for better maintainability

---

## 📋 **COMPATIBILITY CHECKLIST**

### **✅ RUNIQUE Compatibility:**
- [ ] Simple repository injection
- [ ] Direct repository calls in ViewModel init
- [ ] Simple state management
- [ ] Basic error handling

### **✅ Screenly Compatibility:**
- [ ] UseCase pattern
- [ ] MVI with Actions/Events
- [ ] Result wrapper usage
- [ ] Offline-first patterns

### **✅ Our Template Enhancements:**
- [ ] Superior Result wrapper
- [ ] Comprehensive DataError types
- [ ] Enhanced testing infrastructure
- [ ] 20-item checklist compliance
- [ ] Hybrid pattern support

---

## 🎯 **BENEFITS OF OUR APPROACH**

### **1. Backward Compatibility:**
- ✅ Supports existing RUNIQUE projects
- ✅ Supports existing Screenly projects
- ✅ No breaking changes required

### **2. Forward Compatibility:**
- ✅ Enhanced error handling
- ✅ Better testing infrastructure
- ✅ Comprehensive standards
- ✅ Future-proof architecture

### **3. Developer Experience:**
- ✅ Familiar patterns from both projects
- ✅ Gradual migration path
- ✅ Superior tooling and standards
- ✅ Comprehensive documentation

---

## 🔄 **NEXT STEPS**

1. **Choose your compatibility level:**
   - **Simple**: Use `SimpleUseCase` and `SimpleViewModel` (RUNIQUE style)
   - **Enhanced**: Use `ParameterizedUseCase` and `MviViewModel` (Screenly style)
   - **Hybrid**: Use `HybridUseCase` and `HybridViewModel` (Our enhanced style)

2. **Follow the 20-item checklist** for consistency

3. **Leverage our superior implementations** for better maintainability

4. **Gradually migrate** to enhanced patterns as needed

**Our template provides the best of all worlds while maintaining full compatibility!** 🚀
