# Svastha — Sprint 3 Plan

**Sprint Duration:** Monday, October 6 → Thursday, October 9 (shortened; Friday is a day off)

---

## Sprint Goals
This sprint focuses on completing the threshold feature carried over from Sprint 2 and introducing user-editable threshold settings. The goal is to make the threshold logic functional and configurable, establishing the foundation for personalized wellness parameters in future sprints.

---

## Objectives

### 1. Finalize Threshold Visualization (Carryover from Sprint 2)
**Goal:** Render visual threshold markers for both Heart Rate (HR) and Respiratory Rate (RR) charts.

**Tasks:**
- [ ] Define default threshold ranges (e.g., HR: 60–100 bpm, RR: 12–20 breaths/min).
- [ ] Add threshold overlay to Vico line charts.
- [ ] Implement visual distinction for out-of-range points (e.g., color shift or warning markers).
- [ ] Confirm correct scaling and appearance across both charts.
- [ ] Verify data-driven updates still work after thresholds are added.

**Acceptance Criteria:**
- Threshold lines/bands visible for both HR and RR charts.
- Data points exceeding thresholds are clearly highlighted.
- Chart rendering performance remains smooth on emulator.

---

### 2. Implement Editable Thresholds in Settings
**Goal:** Allow users to modify HR/RR threshold values from a Settings screen.

**Tasks:**
- [ ] Create new `SettingsScreen.kt` using Jetpack Compose.
- [ ] Add editable numeric fields for HR and RR thresholds (min and max).
- [ ] Persist values locally via `DataStore`.
- [ ] Update `DashboardViewModel` to read threshold preferences reactively.
- [ ] Reflect changes on charts without needing to restart the app.

**Acceptance Criteria:**
- Settings page loads with default threshold values.
- Users can edit and save thresholds.
- Changes immediately reflect on the dashboard charts.

---

## Out of Scope (Future Sprints)
- Multiple user profiles (Ayurveda dosha-based configurations).
- Adaptive threshold suggestions based on biometric trends.
- Integration with device sensors or external APIs.

---

## Deliverables
- Updated Dashboard UI with visible, data-driven thresholds.
- New Settings screen with editable threshold inputs.
- Local persistence of thresholds using `DataStore`.
- Documentation: update `MVP_SCOPE.md` if feature behavior changes.

---

## Risks & Considerations
- **UI Consistency:** Ensure thresholds visually align with chart axes and data scale.
- **Data Validation:** Prevent nonsensical inputs (e.g., negative HR).
- **State Management:** Maintain real-time sync between settings and dashboard without excessive recompositions.

---

## Timeline & Cadence
- **Sprint Start:** Monday, October 6  
- **Sprint End:** Thursday, October 9  
- **Next Sprint Start:** Monday, October 13  
- **Next Sprint End:** Sunday, October 19  

---

## Version
Document version: `SPRINT_3_PLAN v1.0`
